package ru.project.clerkapp.main.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatFragment
import ru.project.clerkapp.R
import ru.project.clerkapp.main.MainActivity

abstract class BaseLoadingFragment : MvpAppCompatFragment(), BaseLoadingView {

    override fun changeLoadingState(state: Boolean) {
        (activity as MainActivity).changeLoadingState(state)
    }

    override fun returnToPreviousFragment() {
        fragmentManager?.popBackStack()
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun openFragment(fragment: Fragment) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}