package ru.project.clerkapp.entities

import java.io.Serializable

class Message(
    val date: Long = System.currentTimeMillis(),
    val text: String,
    val fromEmail: String,
    val toEmail: String
) : Serializable {

    companion object {
        fun objectToHashMap(message: Message): HashMap<String, Any> {
            val map = HashMap<String, Any>()
            map["date"] = message.date
            map["text"] = message.text
            map["fromEmail"] = message.fromEmail
            map["toEmail"] = message.toEmail
            return map
        }

        fun mapToObject(map: HashMap<String, Any>): Message {
            return Message(
                date = map["date"] as Long,
                text = map["text"] as String,
                fromEmail = map["fromEmail"] as String,
                toEmail = map["toEmail"] as String
            )
        }
    }
}