package ru.project.clerkapp.admin.user_manager.register_user

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import ru.project.clerkapp.entities.User
import java.text.SimpleDateFormat

@InjectViewState
class RegisterUserPresenter : MvpPresenter<RegisterUserView>() {

    fun createNewUser(user: User, password: String) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email, password)
            .addOnSuccessListener {
                it.user?.uid?.let { userUid ->
                    val database = FirebaseFirestore.getInstance()
                    database.collection("users").document(userUid).set(user)
                        .addOnSuccessListener {
                            viewState.showToast("Пользователь создан")
                        }
                        .addOnFailureListener { exception ->
                            viewState.showToast(exception.message.toString())
                        }
                }
            }
            .addOnFailureListener { exception ->
                viewState.showToast(exception.message.toString())
            }
    }

    fun dateToTimeStamp(date: String): Long {
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        formatter.parse(date)?.let {
            return it.time
        }
        return 0
    }
}