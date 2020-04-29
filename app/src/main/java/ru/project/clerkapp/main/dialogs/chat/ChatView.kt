package ru.project.clerkapp.main.dialogs.chat

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingView

interface ChatView : BaseLoadingView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initRecyclerView(currentUser: User)
}