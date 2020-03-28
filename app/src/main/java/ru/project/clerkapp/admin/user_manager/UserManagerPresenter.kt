package ru.project.clerkapp.admin.user_manager

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class UserManagerPresenter : MvpPresenter<UserManagerView>() {

    fun replaceFragment(fragment: Fragment) {
        viewState.openRegisterUserScreen(fragment)
    }
}