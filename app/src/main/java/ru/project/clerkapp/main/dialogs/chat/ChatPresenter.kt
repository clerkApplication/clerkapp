package ru.project.clerkapp.main.dialogs.chat

import com.arellomobile.mvp.InjectViewState
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingPresenter
import ru.project.clerkapp.realm.user.UserGateway

@InjectViewState
class ChatPresenter : BaseLoadingPresenter<ChatView>() {

    private lateinit var currentUser: User


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        currentUser = UserGateway.getCurrentUser()
        initRecyclerView()
        viewState.changeLoadingState(false)
    }

    fun initRecyclerView() {
        viewState.initRecyclerView(currentUser)
    }
}