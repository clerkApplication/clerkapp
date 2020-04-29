package ru.project.clerkapp.main.dialogs.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_chat.*
import ru.project.clerkapp.R
import ru.project.clerkapp.entities.Message
import ru.project.clerkapp.entities.User
import ru.project.clerkapp.main.MainActivity
import ru.project.clerkapp.main.base.BaseLoadingFragment
import ru.project.clerkapp.main.dialogs.chat.recycler_view.ChatAdapter
import ru.project.clerkapp.utils.Constants.USER

class ChatFragment : BaseLoadingFragment(), ChatView {


    @InjectPresenter
    lateinit var presenter: ChatPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onResume() {
        super.onResume()
        val user = arguments?.getParcelable<User>(USER) ?: throw Exception()
        (activity as MainActivity).changeToolbarTitle(
            resources.getString(
                R.string.dialogFullUserName,
                user.lastName,
                user.name
            )
        )
        (activity as MainActivity).changeArrowBackVisibility(true)
        (activity as MainActivity).changeBottomNavigationViewVisibility(false)
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity).changeBottomNavigationViewVisibility(true)
    }

    override fun initRecyclerView(currentUser: User) {
        val a = ArrayList<Message>()
        a.add(Message(1231214214, "qqeqwewq", "worker1@gmail.com", "asdasd"))
        a.add(Message(1231214214, "asqwed", "worker1@gmal.com", "asdasd"))
        a.add(Message(12312214214, "asdasdad", "worker1@gmil.com", "asdasd"))
        a.add(Message(1231214214, "asqweqwed", "worker1@gmail.com", "asdasd"))
        a.add(Message(1231214214, "assadasdd", "worker1@gmil.com", "asdasd"))
        a.add(Message(1231214214, "asdsada", "worker1@gail.com", "asdasd"))
        a.add(Message(12361214214, "asasdadaadsd", "worker1@mail.com", "asdasd"))
        a.add(Message(1231214214, "asdasd", "worker1@gmil.com", "asdasd"))
        a.add(Message(1231214214, "asasdaasdadsd", "worker1.com", "asdasd"))
        a.add(Message(1231214214, "a1231321d", "worker1@gmail.com", "asdasd"))
        a.add(Message(1231214214, "asadadassd", "worker1@gmal.com", "asdasd"))
        a.add(Message(12311214214, "aszxczxcdd", "worker1@gil.com", "asdasd"))
        a.add(Message(1231214214, "asasdasd", "worker1@gmai.com", "asdasd"))
        a.add(Message(12312124214, "adasdsasd", "worker1@gmail.com", "asdasd"))
        a.add(Message(1231214214, "asasdadd", "worker1@gail.com", "asdasd"))
        chatRecyclerView.layoutManager = LinearLayoutManager(activity)
        chatRecyclerView.adapter = ChatAdapter(a, currentUser)
    }
}