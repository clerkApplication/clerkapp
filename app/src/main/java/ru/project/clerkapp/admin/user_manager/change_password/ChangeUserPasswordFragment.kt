package ru.project.clerkapp.admin.user_manager.change_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_change_user_password.*
import ru.project.clerkapp.R
import ru.project.clerkapp.admin.AdminActivity
import ru.project.clerkapp.utils.EditTextUtils.getTextFromEditText
import ru.project.clerkapp.utils.EditTextUtils.setButtonEnableWatcher
import ru.project.clerkapp.utils.EditTextUtils.setDefaultEditTextWatcher

class ChangeUserPasswordFragment : MvpAppCompatFragment(), ChangeUserPasswordView {

    @InjectPresenter
    lateinit var presenter: ChangeUserPasswordPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_change_user_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setWatchers()
    }

    override fun onResume() {
        super.onResume()
        (activity as AdminActivity).changeArrowBackVisibility(true)
        (activity as AdminActivity).changeToolbarTitle(resources.getString(R.string.change_password))
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun backToUserManagerFragment() {
        fragmentManager?.popBackStack()
    }

    override fun changeButtonState(state: Boolean) {
        changeUserPasswordButton.isEnabled = state
    }

    private fun setListeners() {
        changeUserPasswordButton.setOnClickListener {
            presenter.changeUserPassword(
                emailEditText.getTextFromEditText()
            )
        }
    }

    private fun setWatchers() {
        context?.let {
            emailEditText.setDefaultEditTextWatcher(emailUnderline)
            setButtonEnableWatcher(
                changeUserPasswordButton,
                emailEditText
            )
        }
    }
}