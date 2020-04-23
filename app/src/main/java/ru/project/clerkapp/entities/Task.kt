package ru.project.clerkapp.entities

import java.io.Serializable

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val status: TaskStatus,
    val toEmails: List<String>,
    val fromEmail: String,
    val date: Long
) : Serializable {

    companion object {
        fun objectToHashMap(task: Task): HashMap<String, Any> {
            val map = HashMap<String, Any>()
            map["id"] = task.id
            map["title"] = task.title
            map["description"] = task.description
            map["status"] = task.status.getStringFromStatus()
            map["toEmails"] = task.toEmails
            map["fromEmail"] = task.fromEmail
            map["date"] = task.date
            return map
        }

        fun mapToObject(map: HashMap<String, Any>): Task {
            return Task(
                id = map["id"] as String,
                title = map["title"] as String,
                description = map["description"] as String,
                status = getStatusFromString(map["status"] as String),
                toEmails = map["toEmails"] as List<String>,
                fromEmail = map["fromEmail"] as String,
                date = map["date"] as Long
            )
        }

        fun generateIdByCurrentMillis(): String = System.currentTimeMillis().toString()
    }
}