package ru.project.clerkapp.entities

sealed class TaskStatus

object ToPerform : TaskStatus()
object InProgress: TaskStatus()
object Returned: TaskStatus()
object Checking: TaskStatus()
object SentToCheck: TaskStatus()
object Completed: TaskStatus()

fun TaskStatus.getStringFromStatus(): String {
    return when (this) {
        ToPerform -> "К исполнению"
        InProgress -> "В работе"
        Returned -> "Возвращена"
        Checking -> "Проверяется"
        SentToCheck -> "Отправлена на проверку"
        Completed -> "Завершена"
    }
}

fun getStatusFromString(string: String): TaskStatus {
    return when(string) {
        "К исполнению" -> ToPerform
        "В работе" -> InProgress
        "Возвращена" -> Returned
        "Проверяется" -> Checking
        "Отправлена на проверку" -> SentToCheck
        else -> Completed
    }
}
