package ru.project.clerkapp.main.tasks

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.realm.user.UserGateway
import ru.project.clerkapp.utils.Constants.WORKER

@InjectViewState
class TasksPresenter: MvpPresenter<TasksView>() {

    private lateinit var user: User

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user = UserGateway.getCurrentUser()
        hideFloatingActionButton()
    }

    fun openFragment(fragment: Fragment) {
        viewState.openFragment(fragment)
    }

    private fun hideFloatingActionButton() {
        if (user.rank == WORKER) {
            viewState.hideFloatingActionButton()
        }
    }
}