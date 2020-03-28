package ru.project.clerkapp.admin.user_manager.change_password

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ChangeUserPasswordView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(message: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun changeButtonState(state: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun backToUserManagerFragment()
}