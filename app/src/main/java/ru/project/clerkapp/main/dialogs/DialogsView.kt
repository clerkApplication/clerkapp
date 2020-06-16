package ru.project.clerkapp.main.dialogs

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingView

interface DialogsView : BaseLoadingView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initRecyclerView(users: List<User>)
}

