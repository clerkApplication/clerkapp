package ru.project.clerkapp.main

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFragment(fragment: Fragment)
}