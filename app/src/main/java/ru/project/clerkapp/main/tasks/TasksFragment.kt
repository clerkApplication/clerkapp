package ru.project.clerkapp.main.tasks

import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

class TasksFragment: MvpAppCompatFragment(), TasksView {

    @InjectPresenter
    lateinit var presenter: TasksPresenter
}