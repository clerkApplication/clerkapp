package ru.project.clerkapp.admin.user_manager.change_password

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.auth.FirebaseAuth

@InjectViewState
class ChangeUserPasswordPresenter : MvpPresenter<ChangeUserPasswordView>() {

    fun changeUserPassword(email: String) {
        viewState.changeButtonState(false)

        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnSuccessListener {
                viewState.showToast("На электронную почту был отправлено сообщение.")
                viewState.backToUserManagerFragment()
            }
            .addOnFailureListener {
                viewState.showToast(it.message.toString())
                viewState.changeButtonState(true)
            }
    }
}