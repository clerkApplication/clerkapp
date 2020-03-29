package ru.project.clerkapp.main.tasks

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.project.clerkapp.realm.user.UserGateway

@InjectViewState
class TasksPresenter: MvpPresenter<TasksView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        UserGateway.getCurrentUser()
    }
}