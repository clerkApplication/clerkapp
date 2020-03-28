package ru.project.clerkapp.admin.user_manager.register_user

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface RegisterUserView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(message: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun backToUserManagerFragment()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun changeButtonState(state: Boolean)
}