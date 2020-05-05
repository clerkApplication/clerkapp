package ru.project.clerkapp.admin

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.toolbar.*
import ru.project.clerkapp.R
import ru.project.clerkapp.admin.user_manager.UserManagerFragment
import ru.project.clerkapp.login.LoginActivity
import ru.project.clerkapp.utils.ViewUtils.changeVisibilityState

class AdminActivity : MvpAppCompatActivity(), AdminView {

    @InjectPresenter
    lateinit var presenter: AdminPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        setListeners()
    }

    override fun openUserManagerFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, UserManagerFragment())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        FirebaseAuth.getInstance().signOut()
    }

    fun changeArrowBackVisibility(state: Boolean) {
        backArrow.changeVisibilityState(state)
    }

    fun changeToolbarTitle(title: String) {
        toolbarTitle.text = title
    }

    fun logoutFromAdminPanel() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setListeners() {
        backArrow.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }
}