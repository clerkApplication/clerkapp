package ru.project.clerkapp.main.profile.edit_profile

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingPresenter
import ru.project.clerkapp.realm.user.UserGateway
import ru.project.clerkapp.utils.Constants.USERS

@InjectViewState
class EditProfilePresenter : BaseLoadingPresenter<EditProfileView>() {

    private lateinit var user: User
    private lateinit var firebaseUser: FirebaseUser

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user = UserGateway.getCurrentUser()
        viewState.initContent(user)
        viewState.changeLoadingState(false)
    }

    fun editUser(phone: String, email: String) {
        if (!validateFields(phone, email)) {
            return
        }

        viewState.changeLoadingState(true)
        firebaseUser = FirebaseAuth.getInstance().currentUser ?: return
        val cloudDatabase = FirebaseFirestore.getInstance()
        val newUser = updateUserFields(phone, email)
        cloudDatabase.collection(USERS).document(firebaseUser.uid).update(User.objectToHashMap(newUser))
            .addOnSuccessListener {
                updateEmailForFirebaseAuth()
                updateLocalUser(newUser)
                viewState.showToast("Данные успешно отредактированы!")
                viewState.returnToPreviousFragment()
            }
            .addOnFailureListener {
                viewState.showToast(it.message.toString())
            }
            .addOnCompleteListener {
                viewState.changeLoadingState(false)
            }
    }

    private fun updateEmailForFirebaseAuth() {
        firebaseUser.updateEmail(user.email)
    }

    private fun updateUserFields(phone: String, email: String): User {
        return user.copy(phone = phone.toLong(), email = email)
    }

    private fun updateLocalUser(user: User) {
        UserGateway.editUser(user)
    }

    private fun validateFields(phone: String, email: String): Boolean {
        if (phone.isBlank()) {
            viewState.showToast("Заполните номер телефона!")
            return false
        }
        if (phone.length != RUSSIAN_PHONE_LENGTH) {
            viewState.showToast("Номер телефона заполнен некорректно!")
            return false
        }
        if (email.isBlank()) {
            viewState.showToast("Заполните электронную почту!")
            return false
        }
        if (!email.contains(".com") && !email.contains(".ru")) {
            viewState.showToast("Электронная почта заполнена неверно!")
            return false
        }
        return true
    }

    companion object {
        private const val RUSSIAN_PHONE_LENGTH = 11
    }
}