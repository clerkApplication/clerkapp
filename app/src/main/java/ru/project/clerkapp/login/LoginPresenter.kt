package ru.project.clerkapp.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.auth.FirebaseAuth
import ru.project.clerkapp.utils.Constants.ADMIN
import ru.project.clerkapp.utils.Constants.ADMIN_UID

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        checkIsUserSignedIn()
    }

    fun signIn(email: String, password: String) {
        if (email == ADMIN && password == ADMIN) {
            viewState.openAdminActivity()
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    viewState.openMainActivity()
                }
                .addOnFailureListener {
                    viewState.showToast(it.message.toString())
                }
        }
    }

    private fun checkIsUserSignedIn() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null && user.uid != ADMIN_UID) {
            viewState.openMainActivity()
        }
    }
}