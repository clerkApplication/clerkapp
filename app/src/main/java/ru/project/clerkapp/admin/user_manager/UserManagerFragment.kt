package ru.project.clerkapp.admin.user_manager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_user_manager.*
import ru.project.clerkapp.R
import ru.project.clerkapp.admin.AdminActivity
import ru.project.clerkapp.admin.user_manager.change_password.ChangeUserPasswordFragment
import ru.project.clerkapp.admin.user_manager.register_user.RegisterUserFragment

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

    override fun onResume() {
        super.onResume()
        (activity as AdminActivity).changeArrowBackVisibility(false)
        (activity as AdminActivity).changeToolbarTitle(resources.getString(R.string.menu))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        registerUserButton.setOnClickListener {
            presenter.replaceFragment(RegisterUserFragment())
        }
        changeUserPasswordButton.setOnClickListener {
            presenter.replaceFragment(ChangeUserPasswordFragment())
        }
        logoutAdminPanelButton.setOnClickListener {
            (activity as AdminActivity).logoutFromAdminPanel()
        }
    }

    override fun openRegisterUserScreen(fragment: Fragment) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}