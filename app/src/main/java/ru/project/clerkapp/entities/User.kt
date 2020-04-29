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
        fun objectToHashMap(user: User): HashMap<String, Any> {
            val map = HashMap<String, Any>()
            map["name"] = user.name
            map["lastName"] = user.lastName
            map["patronymic"] = user.patronymic
            map["email"] = user.email
            map["rank"] = user.rank
            map["phone"] = user.phone
            map["gender"] = user.gender
            map["birthday"] = user.birthday
            return map
        }

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




















