package ru.project.clerkapp.entities

import ru.project.clerkapp.utils.Constants.CHECKING
import ru.project.clerkapp.utils.Constants.COMPLETED
import ru.project.clerkapp.utils.Constants.IN_PROGRESS
import ru.project.clerkapp.utils.Constants.RETURNED
import ru.project.clerkapp.utils.Constants.SENT_TO_CHECK
import ru.project.clerkapp.utils.Constants.TO_PERFORM
import java.io.Serializable

sealed class TaskStatus : Serializable

object ToPerform : TaskStatus()
object InProgress : TaskStatus()
object Returned : TaskStatus()
object Checking : TaskStatus()
object SentToCheck : TaskStatus()
object Completed : TaskStatus()

fun TaskStatus.getStringFromStatus(): String {
    return when (this) {
        ToPerform -> TO_PERFORM
        InProgress -> IN_PROGRESS
        Returned -> RETURNED
        Checking -> CHECKING
        SentToCheck -> SENT_TO_CHECK
        Completed -> COMPLETED
    }
}

fun getStatusFromString(string: String): TaskStatus {
    return when (string) {
        TO_PERFORM -> ToPerform
        IN_PROGRESS -> InProgress
        RETURNED -> Returned
        CHECKING -> Checking
        SENT_TO_CHECK -> SentToCheck
        else -> Completed
    }
}
