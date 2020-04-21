package ru.project.clerkapp.main.tasks.add_new_task.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task_preformer.view.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.User

class AddNewTaskAdapter(private val users: List<User>) : RecyclerView.Adapter<AddNewTaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_task_preformer, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            with(itemView) {
                performerName.text = user.name
            }
        }
    }
}