package ru.project.clerkapp.main.tasks.add_new_task.add_performer

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingView

interface AddPerformerView: BaseLoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun sendPerformersToPreviousFragment(selectedPerformers: ArrayList<User>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initRecyclerView(performers: List<User>)
}