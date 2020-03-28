package ru.project.clerkapp.admin.user_manager.register_user

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_register_user.*
import ru.project.clerkapp.R
import ru.project.clerkapp.admin.AdminActivity
import ru.project.clerkapp.admin.user_manager.register_user.spinner.RankSpinnerAdapter
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.utils.Extensions.getTextFromEditText
import ru.project.clerkapp.utils.Extensions.setButtonEnableWatcher
import ru.project.clerkapp.utils.Extensions.setDefaultEditTextWatchers
import java.util.*

class RegisterUserFragment : MvpAppCompatFragment(), RegisterUserView {

    @InjectPresenter
    lateinit var presenter: RegisterUserPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_user, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as AdminActivity).changeArrowBackVisibility(true)
        (activity as AdminActivity).changeToolbarTitle(resources.getString(R.string.user_registration))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setSpinner()
        setWatchers()
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun changeButtonState(state: Boolean) {
        createButton.isEnabled = state
    }

    override fun backToUserManagerFragment() {
        fragmentManager?.popBackStack()
    }

    @SuppressLint("SetTextI18n")
    private fun setListeners() {
        createButton.setOnClickListener {
            val gender = if (maleRadioButton.isSelected) {
                getString(R.string.male)
            } else {
                getString(R.string.female)
            }
            val user = User(
                nameEditText.getTextFromEditText(),
                lastNameEditText.getTextFromEditText(),
                patronymicEditText.getTextFromEditText(),
                emailEditText.getTextFromEditText(),
                rankSpinner.selectedItem.toString(),
                phoneEditText.getTextFromEditText().toLong(),
                gender,
                presenter.dateToTimeStamp(birthdayEditText.getTextFromEditText())
            )
            presenter.createNewUser(user, passwordEditText.getTextFromEditText())
        }

        calendarIcon.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


            DatePickerDialog(
                context!!, DatePickerDialog.OnDateSetListener { _, currentYear, monthOfYear, dayOfMonth ->
                    birthdayEditText.setText("$dayOfMonth.$monthOfYear.$currentYear")
                },
                year,
                month,
                day
            ).show()
        }
    }

    private fun setSpinner() {
        rankSpinner.adapter = RankSpinnerAdapter(context!!, resources.getStringArray(R.array.ranks).toList())
    }

    private fun setWatchers() {
        context?.let {
            nameEditText.setDefaultEditTextWatchers(it, nameUnderline)
            lastNameEditText.setDefaultEditTextWatchers(it, lastNameUnderline)
            patronymicEditText.setDefaultEditTextWatchers(it, patronymicUnderline)
            birthdayEditText.setDefaultEditTextWatchers(it, birthdayUnderline)
            phoneEditText.setDefaultEditTextWatchers(it, phoneUnderline)
            emailEditText.setDefaultEditTextWatchers(it, emailUnderline)
            passwordEditText.setDefaultEditTextWatchers(it, passwordUnderline)
            setButtonEnableWatcher(
                createButton,
                nameEditText,
                lastNameEditText,
                patronymicEditText,
                birthdayEditText,
                phoneEditText,
                emailEditText,
                passwordEditText
            )
        }
    }
}