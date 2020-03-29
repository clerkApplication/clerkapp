package ru.project.clerkapp.main.tasks.add_new_task

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.project.clerkapp.entities.User

interface AddNewTaskView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun initRecyclerView(users: List<User>)
}