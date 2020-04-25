package ru.project.clerkapp.main.tasks.task

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.project.clerkapp.main.base.BaseLoadingView

interface TaskView : BaseLoadingView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initContent(status: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideSendToCheckButton()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideValidateTaskLayout()
}