package ru.project.clerkapp.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.auth.FirebaseAuth

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    private val TAG = "LoginPresenter"


    fun checkIsUserSignedIn() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            viewState.openMainActivity()
        }
    }

    fun signIn(login: String, password: String) {
        if (login == "admin" && password == "admin") {
            viewState.openAdminActivity()
        }
    }
}