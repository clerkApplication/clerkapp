package ru.project.clerkapp.entities

data class User(
    val name: String,
    val lastName: String,
    val patronymic: String,
    val email: String,
    val rank: String,
    val phone: Long,
    val gender: String,
    val birthday: Long
)