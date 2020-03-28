package ru.project.clerkapp.admin

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface AdminView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openUserManagerFragment()
}