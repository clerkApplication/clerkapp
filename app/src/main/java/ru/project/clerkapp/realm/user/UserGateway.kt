package ru.project.clerkapp.realm.user

import io.realm.Realm
import ru.project.clerkapp.entities.User

class UserGateway {

    companion object {
        fun getCurrentUser(): User {
            val realm = Realm.getDefaultInstance()
            val user = realm.where(RealmUser::class.java).findFirst()?.toUser()
            return user ?: throw Exception()
        }

        fun saveUser(user: User) {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.copyToRealm(RealmUser.fromUser(user))
            realm.commitTransaction()
        }

        fun removeUser() {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.where(RealmUser::class.java).findFirst()?.deleteFromRealm()
            realm.commitTransaction()
        }
    }
}