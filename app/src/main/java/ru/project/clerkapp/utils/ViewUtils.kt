package ru.project.clerkapp.utils

import android.view.View

object ViewUtils {

    fun View.changeVisibilityState(state: Boolean) {
        if (state) {
            visibility = View.VISIBLE
        } else {
            visibility = View.GONE
        }
    }
}