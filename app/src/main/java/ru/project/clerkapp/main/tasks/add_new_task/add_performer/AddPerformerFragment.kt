package ru.project.clerkapp.main.tasks.add_new_task.add_performer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_add_performer.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.base.BaseLoadingFragment
import ru.project.clerkapp.main.tasks.add_new_task.add_performer.recycler_view.AddPerformerAdapter
import ru.project.clerkapp.utils.Constants.SELECTED_PERFORMERS


class AddPerformerFragment : BaseLoadingFragment(), AddPerformerView {

    @InjectPresenter
    lateinit var presenter: AddPerformerPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_performer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun initRecyclerView(performers: List<User>) {
        performersRecyclerView.layoutManager = LinearLayoutManager(activity)
        performersRecyclerView.adapter = AddPerformerAdapter(performers, object : AddPerformerAdapter.Listener {
            override fun isItemSelected(performer: User, isSelected: Boolean) {
                presenter.isItemSelected(performer, isSelected)
            }
        })
    }

    override fun sendPerformersToPreviousFragment(selectedPerformers: ArrayList<User>) {
        targetFragment!!.onActivityResult(
            targetRequestCode,
            Activity.RESULT_OK,
            Intent().putParcelableArrayListExtra(SELECTED_PERFORMERS, selectedPerformers)
        )
        super.returnToPreviousFragment()
    }

    private fun setListeners() {
        addPerformersButton.isEnabled = true
        addPerformersButton.setOnClickListener {
            presenter.getSelectedPerformers()
        }
    }
}