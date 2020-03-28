package ru.project.clerkapp.admin

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.toolbar.*
import ru.project.clerkapp.R
import ru.project.clerkapp.admin.user_manager.UserManagerFragment
import ru.project.clerkapp.utils.Extensions.changeVisibilityState

class AdminActivity : MvpAppCompatActivity(), AdminView {

    @InjectPresenter
    lateinit var presenter: AdminPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        setListeners()
    }

    fun changeArrowBackVisibility(state: Boolean) {
        backArrow.changeVisibilityState(state)
    }

    fun changeToolbarTitle(title: String) {
        toolbarTitle.text = title
    }

    private fun setListeners() {
        backArrow.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }

    override fun openUserManagerFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, UserManagerFragment())
            .commit()
    }
}