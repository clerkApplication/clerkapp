package ru.project.clerkapp.admin.user_manager.register_user

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.realm.user.UserGateway
import ru.project.clerkapp.utils.Constants.USERS
import java.text.SimpleDateFormat

@InjectViewState
class RegisterUserPresenter : MvpPresenter<RegisterUserView>() {

    fun createNewUser(user: User, password: String) {
        viewState.changeProgressState(true)

        if (!areFieldsValid(user)) {
            viewState.changeProgressState(false)
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email, password)
            .addOnSuccessListener {
                it.user?.uid?.let { userUid ->
                    val database = FirebaseFirestore.getInstance()
                    database.collection(USERS).document(userUid).set(user)
                        .addOnSuccessListener {
                            clearCurrentUser()
                            viewState.showToast("Пользователь создан")
                            viewState.backToUserManagerFragment()
                        }
                        .addOnFailureListener { exception ->
                            viewState.showToast(exception.message.toString())
                            viewState.changeProgressState(false)
                        }
                }
            }
            .addOnFailureListener { exception ->
                viewState.changeProgressState(false)
                viewState.showToast(exception.message.toString())
            }
    }

    @SuppressLint("SimpleDateFormat")
    fun dateToTimeStamp(date: String): Long {
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        formatter.parse(date)?.let {
            return it.time
        }
        return 0
    }

    private fun clearCurrentUser() {
        FirebaseAuth.getInstance().signOut()
        UserGateway.removeUser()
    }

    private fun areFieldsValid(user: User): Boolean {
        if (user.rank == "Должность") {
            viewState.showToast("Выберите должность.")
            return false
        }
        if (user.birthday > System.currentTimeMillis()) {
            viewState.showToast("Выберите корректную дату рождения.")
            return false
        }
        return true
    }
}