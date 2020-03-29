package ru.project.clerkapp.main.tasks.add_new_task

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.firestore.FirebaseFirestore
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.utils.Constants.USERS

@InjectViewState
class AddNewTaskPresenter : MvpPresenter<AddNewTaskView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        initItems()
    }

    private fun initItems() {
        val cloudDatabase = FirebaseFirestore.getInstance()
        cloudDatabase.collection(USERS).whereEqualTo("rank", "Работник").get()
            .addOnSuccessListener {
                val users = ArrayList<User>()
                it.forEach { document ->
                    users.add(User.mapToObject(document.data as HashMap<String, Any>))
                }
                viewState.initRecyclerView(users)
            }
            .addOnFailureListener {

            }
    }
}