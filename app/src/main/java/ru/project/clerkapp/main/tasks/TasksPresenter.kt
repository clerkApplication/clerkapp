package ru.project.clerkapp.main.tasks

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.InjectViewState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import ru.project.clerkapp.entities.Task
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingPresenter
import ru.project.clerkapp.realm.user.UserGateway
import ru.project.clerkapp.utils.Constants.FROM_EMAIL
import ru.project.clerkapp.utils.Constants.TASKS
import ru.project.clerkapp.utils.Constants.TO_EMAILS
import ru.project.clerkapp.utils.Constants.WORKER

@InjectViewState
class TasksPresenter : BaseLoadingPresenter<TasksView>() {

    private lateinit var user: User
    private val tasks = ArrayList<Task>()
    private val cloudDatabase = FirebaseFirestore.getInstance()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user = UserGateway.getCurrentUser()
        hideFloatingActionButton()
    }

    fun openFragment(fragment: Fragment) {
        viewState.openFragment(fragment)
    }

    fun isRoleWorker(): Boolean = user.rank == WORKER

    fun loadTasksDependOnRole() {
        tasks.clear()
        viewState.changeLoadingState(true)
        val searchingParameter = getSearchingParameter()
        if (isRoleWorker()) {
            loadTasksForWorker(searchingParameter)
        } else {
            loadTasksForSuperior(searchingParameter)
        }
    }

    fun removeTask(task: Task) {
        cloudDatabase.collection(TASKS).document(task.id).delete()
            .addOnSuccessListener {
                tasks.remove(task)
                viewState.updateAdapter()
                viewState.showToast("Задача была успешно удалена!")
            }
            .addOnFailureListener {
                viewState.showToast(it.message.toString())
            }
    }

    private fun loadTasksForSuperior(searchingParameter: String) {
        cloudDatabase.collection(TASKS).whereEqualTo(searchingParameter, user.email).get()
            .addOnSuccessListener { response ->
                invokeSuccessQuery(response)
            }
            .addOnFailureListener {
                viewState.showToast(it.message.toString())
            }
            .addOnCompleteListener {
                viewState.changeLoadingState(false)
            }
    }

    private fun loadTasksForWorker(searchingParameter: String) {
        cloudDatabase.collection(TASKS).whereArrayContains(searchingParameter, user.email).get()
            .addOnSuccessListener { response ->
                invokeSuccessQuery(response)
            }
            .addOnFailureListener {
                viewState.showToast(it.message.toString())
            }
            .addOnCompleteListener {
                viewState.changeLoadingState(false)
            }
    }

    private fun invokeSuccessQuery(response: QuerySnapshot) {
        response.documents.forEach { document ->
            tasks.add(Task.mapToObject(document.data as HashMap<String, Any>))
        }
        if (tasks.isEmpty()) {
            viewState.changeStateOfEmptyQueryLayout(true)
        } else {
            viewState.initRecyclerView(tasks, user.rank)
            viewState.changeStateOfEmptyQueryLayout(false)
        }
    }

    private fun getSearchingParameter(): String {
        return if (user.rank == WORKER) {
            TO_EMAILS
        } else {
            FROM_EMAIL
        }
    }

    private fun hideFloatingActionButton() {
        if (user.rank == WORKER) {
            viewState.hideFloatingActionButton()
        }
    }
}