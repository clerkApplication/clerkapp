package ru.project.clerkapp.main.profile

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingView

interface ProfileView: BaseLoadingView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initContent(user: User)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openLoginActivity()
}