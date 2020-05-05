package ru.project.clerkapp.main.dialogs

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.firestore.FirebaseFirestore
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingPresenter
import ru.project.clerkapp.realm.user.UserGateway
import ru.project.clerkapp.utils.Constants.USERS

@InjectViewState
class DialogsPresenter : BaseLoadingPresenter<DialogsView>() {

    private val cloudDatabase = FirebaseFirestore.getInstance()
    private val users = ArrayList<User>()
    private lateinit var currentUser: User

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        currentUser = UserGateway.getCurrentUser()
        getDialogs()
    }

    private fun getDialogs() {
        cloudDatabase.collection(USERS).get()
            .addOnSuccessListener { response ->
                response.documents.forEach { document ->
                    users.add(User.mapToObject(document.data as HashMap<String, Any>))
                }
                users.removeAll { it.email == currentUser.email }
                viewState.initRecyclerView(users)
            }
            .addOnFailureListener {
                viewState.showToast(it.message.toString())
            }
            .addOnCompleteListener {
                viewState.changeLoadingState(false)
            }
    }
}