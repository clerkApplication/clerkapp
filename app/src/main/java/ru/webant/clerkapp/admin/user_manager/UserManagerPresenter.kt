package ru.webant.clerkapp.admin.user_manager

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class UserManagerPresenter : MvpPresenter<UserManagerView>() {

    fun openRegisterUserScreen() {
        viewState.openRegisterUserScreen()
    }
}