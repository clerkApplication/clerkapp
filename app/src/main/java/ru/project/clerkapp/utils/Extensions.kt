package ru.project.clerkapp.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import ru.project.clerkapp.R

object Extensions {

    fun View.changeVisibilityState(state: Boolean) {
        visibility = if (state) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun EditText.setDefaultEditTextWatchers(
        context: Context,
        underline: View
    ) {
        val watcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (it.isNotBlank()) {
                        setBackgroundColor(ContextCompat.getColor(context, R.color.activeEditTextBackground))
                        setTextColor(ContextCompat.getColor(context, R.color.textColor))
                        underline.setBackgroundColor(ContextCompat.getColor(context, R.color.activeOrangeColor))
                    } else {
                        setBackgroundColor(ContextCompat.getColor(context, R.color.inactiveEditTextBackground))
                        setTextColor(ContextCompat.getColor(context, R.color.darkTextColor))
                        underline.setBackgroundColor(ContextCompat.getColor(context, R.color.bottomLineColor))
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        addTextChangedListener(watcher)
    }

    fun setButtonEnableWatcher(button: Button, vararg editTexts: EditText) {
        val watcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                button.isEnabled = editTexts.all { it.text.isNotEmpty() }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

        }

        editTexts.forEach { it.addTextChangedListener(watcher) }
        button.isEnabled = editTexts.all { it.text.isNotEmpty() }
    }

    fun EditText.getTextFromEditText(): String {
        return text.toString()
    }
}