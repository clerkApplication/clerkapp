package ru.project.clerkapp.main.tasks

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface TasksView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFragment(fragment: Fragment)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideFloatingActionButton()
}