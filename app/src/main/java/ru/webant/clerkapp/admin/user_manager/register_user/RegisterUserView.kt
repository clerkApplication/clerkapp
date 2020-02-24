package ru.webant.clerkapp.admin.user_manager.register_user

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface RegisterUserView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(message: String)
}