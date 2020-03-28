package ru.project.clerkapp.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.project.clerkapp.R
import ru.project.clerkapp.main.tasks.TasksFragment
import ru.project.clerkapp.utils.Extensions.changeVisibilityState

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationView()
    }

    override fun onResume() {
        super.onResume()
        backArrow.changeVisibilityState(false)
    }

    private fun setupBottomNavigationView() {
        bottomNavigation.selectedItemId = R.id.action_task
        bottomNavigation.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.action_chat -> {
                    changeBottomNavigationLineVisibility(chatLine)
                }
                R.id.action_task -> {
                    changeBottomNavigationLineVisibility(taskLine)
                    presenter.openFragment(TasksFragment())
                }
                R.id.action_profile -> {
                    changeBottomNavigationLineVisibility(profileLine)
                }
            }
        }
    }

    private fun changeBottomNavigationLineVisibility(chosenLine: View) {
        chatLine.visibility = View.INVISIBLE
        taskLine.visibility = View.INVISIBLE
        profileLine.visibility = View.INVISIBLE
        chosenLine.changeVisibilityState(true)
    }

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
