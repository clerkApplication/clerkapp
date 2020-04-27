package ru.project.clerkapp.main.profile.edit_profile

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingView

interface EditProfileView : BaseLoadingView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initContent(user: User)
}