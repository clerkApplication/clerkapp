package ru.project.clerkapp.main

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.project.clerkapp.main.tasks.TasksFragment

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        openFragment(TasksFragment())
    }

    fun openFragment(fragment: Fragment) {
        viewState.openFragment(fragment)
    }
}