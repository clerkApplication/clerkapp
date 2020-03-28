package ru.project.clerkapp.admin

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class AdminPresenter : MvpPresenter<AdminView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.openUserManagerFragment()
    }
}