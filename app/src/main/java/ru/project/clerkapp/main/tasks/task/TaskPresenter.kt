package ru.project.clerkapp.main.tasks.task

import com.arellomobile.mvp.InjectViewState
import com.google.firebase.firestore.FirebaseFirestore
import ru.project.clerkapp.entities.*
import ru.project.clerkapp.main.base.BaseLoadingPresenter
import ru.project.clerkapp.utils.Constants
import ru.project.clerkapp.utils.Constants.CHECKING
import ru.project.clerkapp.utils.Constants.COMPLETED
import ru.project.clerkapp.utils.Constants.IN_PROGRESS
import ru.project.clerkapp.utils.Constants.RETURNED
import ru.project.clerkapp.utils.Constants.SENT_TO_CHECK
import ru.project.clerkapp.utils.Constants.STATUS

@InjectViewState
class TaskPresenter : BaseLoadingPresenter<TaskView>() {

    private val cloudDatabase = FirebaseFirestore.getInstance()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.changeLoadingState(false)
    }

    fun initContent(taskReference: String, status: TaskStatus, isRoleWorker: Boolean) {
        var newStatus = status.getStringFromStatus()
        if (isRoleWorker) {
            if (status is ToPerform) {
                changeTaskStatus(taskReference = taskReference, taskStatus = IN_PROGRESS, successCallback = null)
                newStatus = IN_PROGRESS
            } else if (status is InProgress || status is Checking || status is Completed) {
                viewState.hideSendToCheckButton()
            } else if (status is Returned) {
                changeTaskStatus(taskReference = taskReference, taskStatus = RETURNED, successCallback = null)
                newStatus = RETURNED
            }
        } else {
            if (status is SentToCheck) {
                changeTaskStatus(taskReference = taskReference, taskStatus = CHECKING, successCallback = null)
                newStatus = CHECKING
            } else if (status !is Checking) {
                viewState.hideValidateTaskLayout()
            }
        }

        viewState.initContent(newStatus)
    }

    fun sendTaskToCheck(taskReference: String) {
        changeTaskStatus(taskReference = taskReference, taskStatus = SENT_TO_CHECK, successCallback = {
            viewState.showToast("Задача была отправлена на проверку")
            viewState.returnToPreviousFragment()
        })
    }

    fun acceptTask(taskReference: String) {
        changeTaskStatus(taskReference = taskReference, taskStatus = COMPLETED, successCallback = {
            viewState.showToast("Задача была выполнена")
            viewState.returnToPreviousFragment()
        })
    }

    fun rejectTask(taskReference: String) {
        changeTaskStatus(taskReference = taskReference, taskStatus = RETURNED, successCallback = {
            viewState.showToast("Задача была возвращена")
            viewState.returnToPreviousFragment()
        })
    }

    private fun changeTaskStatus(successCallback: (() -> Unit)?, taskReference: String, taskStatus: String) {
        viewState.changeLoadingState(true)
        cloudDatabase.collection(Constants.TASKS)
            .document(taskReference)
            .update(STATUS, taskStatus)
            .addOnSuccessListener {
                successCallback?.invoke()
            }
            .addOnFailureListener {
                viewState.showToast(it.message.toString())
            }
            .addOnCompleteListener {
                viewState.changeLoadingState(false)
            }
    }
}