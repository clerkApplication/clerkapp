package ru.project.clerkapp.admin

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.project.clerkapp.R
import ru.project.clerkapp.admin.user_manager.UserManagerFragment

class AdminActivity : MvpAppCompatActivity(), AdminView {

    @InjectPresenter
    lateinit var presenter: AdminPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, UserManagerFragment())
            .commit()
    }
}