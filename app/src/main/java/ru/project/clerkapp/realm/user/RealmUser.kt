package ru.project.clerkapp.realm.user

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ru.project.clerkapp.entities.User

open class RealmUser : RealmObject() {

    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var lastName: String = ""
    var patronymic: String = ""
    var email: String = ""
    var rank: String = ""
    var phone: Long = 0
    var gender: String = ""
    var birthday: Long = 0


    fun toUser(): User {
        return User(
            name = name,
            lastName = lastName,
            patronymic = patronymic,
            email = email,
            rank = rank,
            phone = phone,
            gender = gender,
            birthday = birthday
        )
    }

    companion object {
        fun fromUser(user: User): RealmUser {
            return RealmUser().apply {
                name = user.name
                lastName = user.lastName
                patronymic = user.patronymic
                email = user.email
                rank = user.rank
                phone = user.phone
                gender = user.gender
                birthday = user.birthday
            }
        }
    }
}