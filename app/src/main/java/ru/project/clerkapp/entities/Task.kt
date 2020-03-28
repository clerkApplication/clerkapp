package ru.project.clerkapp.entities

import java.io.Serializable
import java.util.*

data class Task(
    val title: String,
    val description: String,
    val status: String, // enum
    val date: Date
) : Serializable