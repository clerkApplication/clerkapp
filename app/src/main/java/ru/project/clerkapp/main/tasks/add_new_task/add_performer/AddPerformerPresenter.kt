package ru.project.clerkapp.main.tasks.add_new_task.add_performer

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.firestore.FirebaseFirestore
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingPresenter
import ru.project.clerkapp.utils.Constants.RANK
import ru.project.clerkapp.utils.Constants.USERS
import ru.project.clerkapp.utils.Constants.WORKER

@InjectViewState
class AddPerformerPresenter : BaseLoadingPresenter<AddPerformerView>() {

    private val performers = ArrayList<User>()
    private val selectedPerformers = ArrayList<User>()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadPerformers()
    }

    fun isItemSelected(performer: User, isSelected: Boolean) {
        if (isSelected) {
            selectedPerformers.add(performer)
        } else {
            selectedPerformers.remove(performer)
        }
    }

    fun getSelectedPerformers() {
        viewState.sendPerformersToPreviousFragment(selectedPerformers)
    }

    private fun loadPerformers() {
        val cloudDatabase = FirebaseFirestore.getInstance()
        cloudDatabase.collection(USERS).whereEqualTo(RANK, WORKER).get()
            .addOnSuccessListener {
                it.forEach { document ->
                    performers.add(User.mapToObject(document.data as HashMap<String, Any>))
                }
                viewState.changeLoadingState(false)
                viewState.initRecyclerView(performers)
            }
            .addOnFailureListener {
                viewState.showToast(it.message.toString())
            }
    }
}