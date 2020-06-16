package ru.project.clerkapp.main.profile

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.auth.FirebaseAuth
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingPresenter
import ru.project.clerkapp.main.profile.edit_profile.EditProfileFragment
import ru.project.clerkapp.realm.user.UserGateway

@InjectViewState
class ProfilePresenter: BaseLoadingPresenter<ProfileView>() {

    private lateinit var user: User


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.changeLoadingState(false)
    }

    fun initContent() {
        user = UserGateway.getCurrentUser()
        viewState.initContent(user)
    }

    fun logout() {
        UserGateway.removeUser()
        FirebaseAuth.getInstance().signOut()
        viewState.openLoginActivity()
    }

    fun onEditProfileClicked() {
        viewState.openFragment(EditProfileFragment())
    }
}
