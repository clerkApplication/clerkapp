package ru.project.clerkapp.main.dialogs.chat.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_message_from.view.*
import kotlinx.android.synthetic.main.item_message_to.view.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.Message
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.utils.DateUtils.timeStampToTime

class ChatAdapter(
    private val messages: List<Message>,
    private val currentUser: User
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].fromEmail == currentUser.email) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> return MessageToViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_message_to,
                    parent,
                    false
                )
            )
            else -> return MessageFromViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_message_from,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> (holder as MessageToViewHolder).bind(messages[position])
            else -> (holder as MessageFromViewHolder).bind(messages[position])
        }
    }

    inner class MessageToViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(message: Message) {
            itemView.chatMessageTo.text = message.message
            itemView.timeMessageTo.text = message.date.timeStampToTime()
        }
    }

    inner class MessageFromViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(message: Message) {
            itemView.chatMessageFrom.text = message.message
            itemView.timeMessageFrom.text = message.date.timeStampToTime()
        }
    }
}