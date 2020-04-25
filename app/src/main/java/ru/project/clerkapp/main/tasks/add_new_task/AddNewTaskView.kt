package ru.project.clerkapp.main.tasks.add_new_task

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingView

interface AddNewTaskView : BaseLoadingView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initRecyclerView(users: ArrayList<User>)
}