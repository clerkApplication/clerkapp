package ru.project.clerkapp.main.tasks.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_task.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.Task
import ru.project.clerkapp.main.MainActivity
import ru.project.clerkapp.main.base.BaseLoadingFragment
import ru.project.clerkapp.utils.Constants
import ru.project.clerkapp.utils.Constants.TASK
import ru.project.clerkapp.utils.DateUtils.timeStampToDate
import ru.project.clerkapp.utils.ViewUtils.changeVisibilityState

class TaskFragment : BaseLoadingFragment(), TaskView {

    private lateinit var task: Task
    private var isRoleWorker: Boolean = false


    @InjectPresenter
    lateinit var presenter: TaskPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        task = arguments?.getSerializable(TASK) as Task
        isRoleWorker = arguments?.getBoolean(Constants.WORKER) ?: false
        changeButtonIfNotWorker()
        presenter.initContent(task.id, task.status, isRoleWorker)
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).changeToolbarTitle(resources.getString(R.string.task))
        (activity as MainActivity).changeArrowBackVisibility(true)
    }

    override fun initContent(status: String) {
        taskTitle.text = task.title
        taskDescription.text = task.description
        taskStatus.text = status
        taskDeadline.text = task.date.timeStampToDate()
    }

    override fun hideSendToCheckButton() {
        sendToCheckButton.changeVisibilityState(false)
    }

    override fun hideValidateTaskLayout() {
        validateTaskLayout.changeVisibilityState(false)
    }

    private fun changeButtonIfNotWorker() {
        if (!isRoleWorker) {
            validateTaskLayout.changeVisibilityState(true)
            sendToCheckButton.changeVisibilityState(false)
        }
    }

    private fun setListeners() {
        sendToCheckButton.setOnClickListener {
            presenter.sendTaskToCheck(task.id)
        }
        acceptButton.setOnClickListener {
            presenter.acceptTask(task.id)
        }
        rejectButton.setOnClickListener {
            presenter.rejectTask(task.id)
        }
    }
}