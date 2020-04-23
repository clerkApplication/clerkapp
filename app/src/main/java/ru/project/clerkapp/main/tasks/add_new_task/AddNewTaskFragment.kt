package ru.project.clerkapp.main.tasks.add_new_task

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_add_new_task.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.Task
import ru.project.clerkapp.entities.ToPerform
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.MainActivity
import ru.project.clerkapp.main.base.BaseLoadingFragment
import ru.project.clerkapp.main.tasks.add_new_task.add_performer.AddPerformerFragment
import ru.project.clerkapp.main.tasks.add_new_task.recycler_view.AddNewTaskAdapter
import ru.project.clerkapp.utils.Constants
import ru.project.clerkapp.utils.EditTextUtils.getTextFromEditText
import ru.project.clerkapp.utils.EditTextUtils.setButtonEnableWatcher
import ru.project.clerkapp.utils.EditTextUtils.setDefaultEditTextWatcher
import ru.project.clerkapp.utils.EditTextUtils.setEditTextWithoutUnderlineWatcher
import ru.project.clerkapp.utils.DateUtils.dateToTimeStamp
import java.util.*

class AddNewTaskFragment : BaseLoadingFragment(), AddNewTaskView {

    @InjectPresenter
    lateinit var presenter: AddNewTaskPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_new_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setWatchers()
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).changeToolbarTitle(resources.getString(R.string.task))
        (activity as MainActivity).changeArrowBackVisibility(true)
    }

    override fun initRecyclerView(users: List<User>) {
        performersRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        performersRecyclerView.adapter = AddNewTaskAdapter(users)
    }

    override fun openFragment(fragment: Fragment) {
        fragment.setTargetFragment(this, TARGET_FRAGMENT_REQUEST_CODE)
        super.openFragment(fragment)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TARGET_FRAGMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            presenter.updatePerformersList(data.getParcelableArrayListExtra<User>(Constants.SELECTED_PERFORMERS))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setListeners() {
        performersListContainer.setOnClickListener {
            openFragment(AddPerformerFragment())
        }

        deadLineEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(
                context!!, DatePickerDialog.OnDateSetListener { _, currentYear, monthOfYear, dayOfMonth ->
                    deadLineEditText.setText("$dayOfMonth.$monthOfYear.$currentYear")
                }, year, month, day
            ).show()
        }

        createButton.setOnClickListener {
            val task = Task(
                id = Task.generateIdByCurrentMillis(),
                title = titleEditText.getTextFromEditText(),
                description = taskDescriptionEditText.getTextFromEditText(),
                status = ToPerform,
                toEmails = presenter.getRecipientsEmail(),
                fromEmail = presenter.getSenderEmail(),
                date = deadLineEditText.getTextFromEditText().dateToTimeStamp()
            )
            presenter.createNewTask(task)
        }
    }

    private fun setWatchers() {
        titleEditText.setDefaultEditTextWatcher(titleUnderline)
        deadLineEditText.setDefaultEditTextWatcher(deadLineUnderline)

        taskDescriptionEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (it.isNotBlank()) {
                        taskDescriptionEditText.setTextColor(
                            ContextCompat.getColor(
                                context!!,
                                R.color.defaultTextColor
                            )
                        )
                    } else {
                        taskDescriptionEditText.setTextColor(ContextCompat.getColor(context!!, R.color.darkTextColor))
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        deadLineEditText.setEditTextWithoutUnderlineWatcher()
        setButtonEnableWatcher(
            createButton,
            titleEditText,
            deadLineEditText,
            taskDescriptionEditText
        )
    }

    companion object {
        const val TARGET_FRAGMENT_REQUEST_CODE = 1111
    }
}