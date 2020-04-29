package ru.project.clerkapp.entities

import java.io.Serializable

class Message(
    val date: Long,
    val message: String,
    val fromEmail: String,
    val toEmail: String
) : Serializable {

    companion object {
        fun objectToHashMap(message: Message): HashMap<String, Any> {
            val map = HashMap<String, Any>()
            map["date"] = message.date
            map["message"] = message.message
            map["fromEmail"] = message.fromEmail
            map["toEmail"] = message.toEmail
            return map
        }

        fun mapToObject(map: HashMap<String, Any>): Message {
            return Message(
                date = map["date"] as Long,
                message = map["message"] as String,
                fromEmail = map["fromEmail"] as String,
                toEmail = map["toEmail"] as String
            )
        }
    }
}