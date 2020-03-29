package ru.project.clerkapp.main.tasks.add_new_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_add_new_task.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.tasks.add_new_task.recycler_view.AddNewTaskAdapter

class AddNewTaskFragment : MvpAppCompatFragment(), AddNewTaskView {

    @InjectPresenter
    lateinit var presenter: AddNewTaskPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_new_task, container, false)
    }

    override fun initRecyclerView(users: List<User>) {
        performersRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        performersRecyclerView.adapter = AddNewTaskAdapter(users)
    }
}