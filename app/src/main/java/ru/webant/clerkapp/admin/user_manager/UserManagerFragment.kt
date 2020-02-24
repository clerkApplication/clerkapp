package ru.webant.clerkapp.admin.user_manager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_user_manager.*
import ru.webant.clerkapp.R
import ru.webant.clerkapp.admin.user_manager.register_user.RegisterUserFragment

class UserManagerFragment : MvpAppCompatFragment(), UserManagerView {

    @InjectPresenter
    lateinit var presenter: UserManagerPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        registerUserButton.setOnClickListener {
            presenter.openRegisterUserScreen()
        }
    }

    override fun openRegisterUserScreen() {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, RegisterUserFragment())
            ?.addToBackStack(null)
            ?.commit()
    }
}