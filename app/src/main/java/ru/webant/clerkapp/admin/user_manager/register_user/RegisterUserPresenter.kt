package ru.webant.clerkapp.admin.user_manager.register_user

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.auth.FirebaseAuth

@InjectViewState
class RegisterUserPresenter : MvpPresenter<RegisterUserView>() {

    fun createNewUser(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                viewState.showToast("Профиль создан!")
            }
            .addOnFailureListener {
                viewState.showToast(it.message!!)
            }
    }
}