package ru.project.clerkapp.main.dialogs.chat

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.firestore.FirebaseFirestore
import ru.project.clerkapp.entities.Message
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingPresenter
import ru.project.clerkapp.realm.user.UserGateway
import ru.project.clerkapp.utils.Constants.DIALOGS
import ru.project.clerkapp.utils.Constants.MESSAGES

@InjectViewState
class ChatPresenter : BaseLoadingPresenter<ChatView>() {

    private val messages = ArrayList<Message>()
    private val cloudDatabase = FirebaseFirestore.getInstance()
    private lateinit var currentUser: User
    private lateinit var userReceiver: User
    private lateinit var documentReference: String


    fun setUsers(userReceiver: User) {
        currentUser = UserGateway.getCurrentUser()
        this.userReceiver = userReceiver
    }

    fun getDocumentReference() {
        viewState.changeLoadingState(true)
        cloudDatabase.collection(DIALOGS).document("${currentUser.email}&${userReceiver.email}").get()
            .addOnCompleteListener { task ->
                task.result?.let {
                    if (!it.exists()) {
                        documentReference = "${currentUser.email}&${userReceiver.email}"
                    } else {
                        documentReference = "${userReceiver.email}&${currentUser.email}"
                    }
                }
                viewState.changeLoadingState(false)
                subscribeOnMessages()
            }
    }

    fun sendMessage(text: String) {
        val message = Message(
            text = text,
            fromEmail = currentUser.email,
            toEmail = userReceiver.email
        )
        cloudDatabase.collection(DIALOGS)
            .document(documentReference)
            .collection(MESSAGES)
            .document(message.date.toString())
            .set(Message.objectToHashMap(message))
    }

    private fun subscribeOnMessages() {
        cloudDatabase.collection(DIALOGS).document(documentReference).collection(MESSAGES)
            .addSnapshotListener { querySnapshot, _ ->
                val documents = querySnapshot?.documents ?: return@addSnapshotListener
                if (documents.isEmpty()) {
                    viewState.changeEmptyMessagesStubState(true)
                } else {
                    messages.clear()
                    viewState.changeEmptyMessagesStubState(false)
                    documents.forEach { document ->
                        messages.add(Message.mapToObject(document.data as HashMap<String, Any>))
                    }
                    viewState.initRecyclerView(currentUser, messages)
                }
            }
    }
}