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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setSpinner()
        setWatchers()
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
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
                getTextFromEditText(nameEditText),
                getTextFromEditText(lastNameEditText),
                getTextFromEditText(patronymicEditText),
                getTextFromEditText(emailEditText),
                rankSpinner.selectedItem.toString(),
                getTextFromEditText(phoneEditText).toLong(),
                gender,
                presenter.dateToTimeStamp(getTextFromEditText(birthdayEditText))
            )
            presenter.createNewUser(user, getTextFromEditText(passwordEditText))
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
            setDefaultEditTextWatchers(it, nameEditText, nameUnderline)
            setDefaultEditTextWatchers(it, lastNameEditText, lastNameUnderline)
            setDefaultEditTextWatchers(it, patronymicEditText, patronymicUnderline)
            setDefaultEditTextWatchers(it, birthdayEditText, birthdayUnderline)
            setDefaultEditTextWatchers(it, phoneEditText, phoneUnderline)
            setDefaultEditTextWatchers(it, emailEditText, emailUnderline)
            setDefaultEditTextWatchers(it, passwordEditText, passwordUnderline)
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