package ru.project.clerkapp.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_login.*
import ru.project.clerkapp.R
import ru.project.clerkapp.admin.AdminActivity
import ru.project.clerkapp.main.MainActivity
import ru.project.clerkapp.utils.EditTextUtils.getTextFromEditText

class LoginActivity : MvpAppCompatActivity(), LoginView {

    @InjectPresenter
    lateinit var presenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        enterButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        handleEditTextsChangeListener()
        setListeners()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun openMainActivity() {
        openActivityWithFinish(MainActivity())
    }

    override fun openAdminActivity() {
        openActivityWithFinish(AdminActivity())
    }

    private fun openActivityWithFinish(activity: MvpAppCompatActivity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setListeners() {
        enterButton.setOnClickListener {
            val email = emailEditText.getTextFromEditText()
            val password = passwordEditText.getTextFromEditText()
            presenter.signIn(email, password)
        }
    }

    private fun handleEditTextsChangeListener() {
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(newText: Editable?) {
                changeContainerStyle(newText.toString(), emailEditText, emailIcon)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(newText: Editable?) {
                changeContainerStyle(newText.toString(), passwordEditText, passwordIcon)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun changeContainerStyle(text: String, editText: EditText, icon: ImageView) {
        if (text.isNotBlank()) {
            setBackgroundColor(editText, R.color.veryLowTransparentWhiteColor)
            setBackgroundColor(icon, R.color.lowTransparentWhiteColor)
        } else {
            setBackgroundColor(editText, R.color.lowTransparentWhiteColor)
            setBackgroundColor(icon, R.color.strongTransparentWhiteColor)
        }
    }

    private fun setBackgroundColor(view: View, color: Int) {
        view.setBackgroundColor(
            ContextCompat.getColor(
                this,
                color
            )
        )
    }
}