package ru.project.clerkapp.main.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_dialogs.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.MainActivity
import ru.project.clerkapp.main.base.BaseLoadingFragment
import ru.project.clerkapp.main.dialogs.chat.ChatFragment
import ru.project.clerkapp.main.dialogs.recycler_view.DialogsAdapter
import ru.project.clerkapp.utils.Constants.USER

class DialogsFragment : BaseLoadingFragment(), DialogsView {

    @InjectPresenter
    lateinit var presenter: DialogsPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialogs, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).changeToolbarTitle(resources.getString(R.string.dialogs))
        (activity as MainActivity).changeArrowBackVisibility(false)
    }

    override fun initRecyclerView(users: List<User>) {
        dialogsRecyclerView.layoutManager = LinearLayoutManager(context)
        dialogsRecyclerView.adapter = DialogsAdapter(users, object : DialogsAdapter.Listener {
            override fun openChatFragment(user: User) {
                val bundle = Bundle()
                val fragment = ChatFragment()
                bundle.putParcelable(USER, user)
                fragment.arguments = bundle
                openFragment(fragment)
            }
        })
    }
}
