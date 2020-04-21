package ru.project.clerkapp.main.base

import com.arellomobile.mvp.MvpPresenter

abstract class BaseLoadingPresenter<T : BaseLoadingView> : MvpPresenter<T>() {

    override fun onFirstViewAttach() {
        viewState.changeLoadingState(true)
        super.onFirstViewAttach()
    }
}