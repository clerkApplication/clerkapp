package ru.project.clerkapp.main.tasks.add_new_task.add_performer.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_performer.view.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.User

class AddPerformerAdapter(private val performers: List<User>, private val listener: Listener) :
    RecyclerView.Adapter<AddPerformerAdapter.ViewHolder>() {

    interface Listener {
        fun isItemSelected(performer: User, isSelected: Boolean)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_performer, parent, false))
    }

    override fun getItemCount(): Int {
        return performers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(performers[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.performerCheckBox.setOnCheckedChangeListener { _, isChecked ->
                listener.isItemSelected(performers[adapterPosition], isChecked)
            }
        }

        fun bind(performer: User) {
            itemView.performerName.text = "${performer.name} ${performer.lastName}"
        }
    }
}