package ru.project.clerkapp.admin.user_manager.register_user.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.rank_spinner.view.*
import ru.project.clerkapp.R
import ru.project.clerkapp.utils.ViewUtils.changeVisibilityState

class RankSpinnerAdapter(
    context: Context,
    private val ranks: List<String>
) : ArrayAdapter<String>(context, 0, ranks) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getDropDownCustomView(position, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    private fun getDropDownCustomView(position: Int, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.rank_spinner, parent, false)

        if (position == 0) {
            view.spinnerArrowImageView.changeVisibilityState(true)
            view.spinnerArrowImageView.setImageResource(R.drawable.ic_arrow_up)
            view.rankTextView.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.inactiveViewColor
                )
            )
        }

        view.rankTextView.text = ranks[position]

        return view
    }

    private fun getCustomView(position: Int, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.rank_spinner, parent, false)

        view.spinnerArrowImageView.changeVisibilityState(true)

        view.rankTextView.text = ranks[position]

        return view
    }
}
