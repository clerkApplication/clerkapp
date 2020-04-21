package ru.project.clerkapp.main.base

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface BaseLoadingView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun returnToPreviousFragment()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(message: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFragment(fragment: Fragment)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun changeLoadingState(state: Boolean)
}