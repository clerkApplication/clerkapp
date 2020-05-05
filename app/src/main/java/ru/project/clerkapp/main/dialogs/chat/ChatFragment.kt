package ru.project.clerkapp.main.dialogs.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
import ru.project.clerkapp.utils.EditTextUtils.getTextFromEditText
import ru.project.clerkapp.utils.ViewUtils.changeVisibilityState

class ChatFragment : BaseLoadingFragment(), ChatView {

    @InjectPresenter
    lateinit var presenter: ChatPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<User>(USER)?.let { presenter.setUsers(it) }
        presenter.getDocumentReference()
        setListeners()
        setWatchers()
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

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).changeBottomNavigationViewVisibility(true)
    }

    override fun changeEmptyMessagesStubState(state: Boolean) {
        emptyMessagesStub.changeVisibilityState(state)
    }

    override fun initRecyclerView(currentUser: User, messages: List<Message>) {
        chatRecyclerView.layoutManager = LinearLayoutManager(activity)
        chatRecyclerView.adapter = ChatAdapter(messages, currentUser)
        chatRecyclerView.scrollToPosition(messages.size - 1)
    }

    private fun setListeners() {
        sendMessageButton.setOnClickListener {
            presenter.sendMessage(messageEditText.getTextFromEditText())
            messageEditText.text.clear()
        }
    }

    private fun setWatchers() {
        sendMessageButton.isEnabled = false

        messageEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrBlank()) {
                    sendMessageButton.setImageDrawable(
                        ContextCompat.getDrawable(
                            activity!!,
                            R.drawable.ic_inactive_send
                        )
                    )
                    sendMessageButton.isEnabled = false
                } else {
                    sendMessageButton.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.ic_active_send))
                    sendMessageButton.isEnabled = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}