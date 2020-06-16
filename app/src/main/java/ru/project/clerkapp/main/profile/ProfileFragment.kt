package ru.project.clerkapp.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_profile.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.MainActivity
import ru.project.clerkapp.main.base.BaseLoadingFragment

class ProfileFragment : BaseLoadingFragment(), ProfileView {

    @InjectPresenter
    lateinit var presenter: ProfilePresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        presenter.initContent()
        (activity as MainActivity).changeToolbarTitle(resources.getString(R.string.profile))
        (activity as MainActivity).changeArrowBackVisibility(false)
    }

    override fun initContent(user: User) {
        profileNameValue.text = getString(R.string.profileFullUserName, user.lastName, user.name, user.patronymic)
        profilePhoneValue.text = user.phone.toString()
        profileEmailValue.text = user.email
    }

    override fun openLoginActivity() {
        (activity as MainActivity).openLoginActivity()
    }

    private fun setListeners() {
        editProfileButton.setOnClickListener {
            presenter.onEditProfileClicked()
        }
        logoutButton.setOnClickListener {
            presenter.logout()
        }
    }
}
