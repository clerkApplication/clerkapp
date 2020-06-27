package ru.project.clerkapp.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.realm.user.UserGateway
import ru.project.clerkapp.utils.Constants.ADMIN
import ru.project.clerkapp.utils.Constants.ADMIN_UID
import ru.project.clerkapp.utils.Constants.USERS

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        checkIsUserSignedIn()
    }

    fun signIn(email: String, password: String) {
        if (!areFieldsValid(email, password)) {
            return
        }
        if (email == ADMIN && password == ADMIN) {
            viewState.openAdminActivity()
        } else {
            viewState.changeProgressBarState(true)
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    saveUserFromCloudFirestore(it.user!!.uid)
                }
                .addOnFailureListener {
                    viewState.showToast(it.message.toString())
                    viewState.changeProgressBarState(false)
                }
        }
    }

    private fun areFieldsValid(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            viewState.showToast("Заполните электронную почту!")
            return false
        }
        if (password.isEmpty()) {
            viewState.showToast("Заполните пароль!")
            return false
        }
        return true
    }

    private fun saveUserFromCloudFirestore(uid: String) {
        val cloudDatabase = FirebaseFirestore.getInstance()
        cloudDatabase.collection(USERS).document(uid).get()
            .addOnSuccessListener {
                UserGateway.saveUser(User.mapToObject(it.data as HashMap<String, Any>))
                viewState.openMainActivity()
            }
            .addOnFailureListener {
                viewState.showToast(it.message.toString())
            }
            .addOnCompleteListener {
                viewState.changeProgressBarState(false)
            }
    }

    private fun checkIsUserSignedIn() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null && user.uid != ADMIN_UID) {
            viewState.openMainActivity()
        }
    }
}