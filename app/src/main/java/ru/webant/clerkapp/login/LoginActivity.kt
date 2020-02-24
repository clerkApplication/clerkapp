package ru.webant.clerkapp.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_login.*
import ru.webant.clerkapp.R
import ru.webant.clerkapp.main.MainActivity

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
    }

    private fun handleEditTextsChangeListener() {
        loginEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(newText: Editable?) {
                changeContainerStyle(newText.toString(), loginEditText, loginIcon)
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