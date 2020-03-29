package ru.project.clerkapp.main.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_tasks.*
import ru.project.clerkapp.R
import ru.project.clerkapp.main.MainActivity
import ru.project.clerkapp.main.tasks.add_new_task.AddNewTaskFragment

class TasksFragment: MvpAppCompatFragment(), TasksView {

    @InjectPresenter
    lateinit var presenter: TasksPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).changeToolbarTitle(resources.getString(R.string.tasks))
    }

    override fun hideFloatingActionButton() {
        addNewTask.visibility = View.GONE
    }

    override fun openFragment(fragment: Fragment) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, fragment)
            ?.commit()
    }

    private fun setListeners() {
        addNewTask.setOnClickListener {
            presenter.openFragment(AddNewTaskFragment())
        }
    }
}