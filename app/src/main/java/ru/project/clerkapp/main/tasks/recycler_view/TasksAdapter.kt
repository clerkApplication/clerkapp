package ru.project.clerkapp.main.tasks.recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.*
import ru.project.clerkapp.utils.Constants.WORKER
import ru.project.clerkapp.utils.DateUtils.timeStampToDate

class TasksAdapter(
    private val tasks: List<Task>,
    private val rank: String,
    private val listener: Listener
) : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {

    interface Listener {
        fun openTask(task: Task)
        fun removeTask(task: Task)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        return TasksViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    inner class TasksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                listener.openTask(tasks[adapterPosition])
            }
            itemView.taskRemoveImageView.setOnClickListener {
                listener.removeTask(tasks[adapterPosition])
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(task: Task) {
            with(itemView) {
                taskTitle.text = task.title
                taskDescription.text = task.description
                taskDeadLine.text = "Срок: ${task.date.timeStampToDate()}"
                taskStatus.text = task.status.getStringFromStatus()

                if (rank == WORKER) {
                    taskRemoveImageView.visibility = View.INVISIBLE
                }
            }
        }

        private fun TaskStatus.bindStatus() {
            when (this) {
                ToPerform -> {

                }
                InProgress -> {

                }
                Returned -> {

                }
                Checking -> {

                }
                SentToCheck -> {

                }
                Completed -> {

                }
            }
        }
    }
}