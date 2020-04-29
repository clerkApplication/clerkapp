package ru.project.clerkapp.main.dialogs.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_dialog.view.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.User

class DialogsAdapter(private val users: List<User>, private val listener: Listener) :
    RecyclerView.Adapter<DialogsAdapter.DialogsViewHolder>() {

    interface Listener {
        fun openChatFragment(user: User)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogsViewHolder {
        return DialogsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_dialog, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: DialogsViewHolder, position: Int) {
        holder.bind(users[position])
    }

    inner class DialogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                listener.openChatFragment(users[adapterPosition])
            }
        }

        fun bind(user: User) {
            itemView.dialogNameTextView.text =
                itemView.resources.getString(R.string.dialogFullUserName, user.lastName, user.name)
        }
    }
}