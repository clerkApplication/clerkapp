package ru.project.clerkapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    @SuppressLint("SimpleDateFormat")
    fun Long.timeStampToDate(): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val date = Date(this)
        return sdf.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun String.dateToTimeStamp(): Long {
        val formatter = SimpleDateFormat(Constants.DATE_FORMAT)
        formatter.parse(this)?.let {
            return it.time
        }
        return 0
    }
}