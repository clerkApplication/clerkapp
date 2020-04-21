package ru.project.clerkapp.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val name: String,
    val lastName: String,
    val patronymic: String,
    val email: String,
    val rank: String,
    val phone: Long,
    val gender: String,
    val birthday: Long
) : Parcelable {

    companion object {
        fun mapToObject(map: HashMap<String, Any>): User {
            return User(
                name = map["name"] as String,
                lastName = map["lastName"] as String,
                patronymic = map["patronymic"] as String,
                email = map["email"] as String,
                rank = map["rank"] as String,
                phone = map["phone"] as Long,
                gender = map["gender"] as String,
                birthday = map["birthday"] as Long
            )
        }
    }
}




















