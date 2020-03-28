package ru.project.clerkapp.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface LoginView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openMainActivity()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openAdminActivity()
}