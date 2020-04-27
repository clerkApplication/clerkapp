package ru.project.clerkapp.main.profile.edit_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.MainActivity
import ru.project.clerkapp.main.base.BaseLoadingFragment
import ru.project.clerkapp.utils.EditTextUtils.getTextFromEditText
import ru.project.clerkapp.utils.EditTextUtils.setButtonEnableWatcher
import ru.project.clerkapp.utils.EditTextUtils.setDefaultEditTextWatcher

class EditProfileFragment : BaseLoadingFragment(), EditProfileView {

    @InjectPresenter
    lateinit var presenter: EditProfilePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setWatchers()
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).changeToolbarTitle(resources.getString(R.string.editing_profile))
        (activity as MainActivity).changeArrowBackVisibility(false)
    }

    override fun initContent(user: User) {
        profilePhoneEditText.setText(user.phone.toString())
        profileEmailEditText.setText(user.email)
    }

    private fun setListeners() {
        editButton.setOnClickListener {
            presenter.editUser(
                profilePhoneEditText.getTextFromEditText(),
                profileEmailEditText.getTextFromEditText()
            )
        }
    }

    private fun setWatchers() {
        profilePhoneEditText.setDefaultEditTextWatcher(phoneUnderline)
        profileEmailEditText.setDefaultEditTextWatcher(emailUnderline)
        setButtonEnableWatcher(
            editButton,
            profilePhoneEditText,
            profileEmailEditText
        )
    }
}