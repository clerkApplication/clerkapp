package ru.project.clerkapp.admin.user_manager

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface UserManagerView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openRegisterUserScreen(fragment: Fragment)
}