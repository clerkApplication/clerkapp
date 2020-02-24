package ru.webant.clerkapp.admin

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.webant.clerkapp.R

class AdminActivity : MvpAppCompatActivity(), AdminView {

    @InjectPresenter
    lateinit var presenter: AdminPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
    }
}