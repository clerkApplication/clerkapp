package ru.project.clerkapp.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.project.clerkapp.R
import ru.project.clerkapp.main.chat.ChatFragment
import ru.project.clerkapp.main.profile.ProfileFragment
import ru.project.clerkapp.main.tasks.TasksFragment
import ru.project.clerkapp.utils.ViewUtils.changeVisibilityState

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationView()
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        backArrow.changeVisibilityState(false)
    }

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    fun changeArrowBackVisibility(state: Boolean) {
        backArrow.changeVisibilityState(state)
    }

    fun changeLoadingState(state: Boolean) {
        progressBar.changeVisibilityState(state)
        container.changeVisibilityState(!state)
    }

    fun changeToolbarTitle(title: String) {
        toolbarTitle.text = title
    }

    private fun setListeners() {
        backArrow.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }

    private fun setupBottomNavigationView() {
        bottomNavigation.selectedItemId = R.id.action_task
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_chat -> {
                    changeBottomNavigationLineVisibility(chatLine)
                    presenter.openFragment(ChatFragment())
                    true
                }
                R.id.action_task -> {
                    changeBottomNavigationLineVisibility(taskLine)
                    presenter.openFragment(TasksFragment())
                    true
                }
                R.id.action_profile -> {
                    changeBottomNavigationLineVisibility(profileLine)
                    presenter.openFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun changeBottomNavigationLineVisibility(chosenLine: View) {
        chatLine.visibility = View.INVISIBLE
        taskLine.visibility = View.INVISIBLE
        profileLine.visibility = View.INVISIBLE
        chosenLine.changeVisibilityState(true)
    }
}
