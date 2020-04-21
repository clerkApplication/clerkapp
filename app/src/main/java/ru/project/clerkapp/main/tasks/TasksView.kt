package ru.project.clerkapp.main.tasks

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.project.clerkapp.entities.Task
import ru.project.clerkapp.main.base.BaseLoadingView

interface TasksView : BaseLoadingView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideFloatingActionButton()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initRecyclerView(tasks: List<Task>, rank: String)
}