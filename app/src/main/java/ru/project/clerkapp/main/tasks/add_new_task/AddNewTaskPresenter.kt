package ru.project.clerkapp.main.tasks.add_new_task

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.firestore.FirebaseFirestore
import ru.project.clerkapp.entities.Task
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingPresenter
import ru.project.clerkapp.realm.user.UserGateway
import ru.project.clerkapp.utils.Constants.TASKS

@InjectViewState
class AddNewTaskPresenter : BaseLoadingPresenter<AddNewTaskView>() {

    private var performers = ArrayList<User>()

    override fun onFirstViewAttach() {}

    fun updatePerformersList(performers: ArrayList<User>) {
        this.performers.clear()
        this.performers = performers
        viewState.initRecyclerView(this.performers)
    }

    fun createNewTask(task: Task) {
        val cloudDatabase = FirebaseFirestore.getInstance()
        val taskHashMap = Task.objectToHashMap(task)
        cloudDatabase.collection(TASKS).document().set(taskHashMap)
            .addOnSuccessListener {
                viewState.showToast("Задача была отправлена")
                viewState.returnToPreviousFragment()
            }
            .addOnFailureListener {
                viewState.showToast(it.message.toString())
            }
    }

    fun getRecipientsEmail(): List<String> {
        val toEmails = ArrayList<String>()
        performers.forEach {
            toEmails.add(it.email)
        }
        return toEmails
    }

    fun getSenderEmail(): String {
        return UserGateway.getCurrentUser().email
    }
}