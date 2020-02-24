package ru.webant.clerkapp.admin.user_manager

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface UserManagerView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openRegisterUserScreen()
}