package examples.factory

import org.instancio.Instancio

class Users() {

    companion object {

        val user = Instancio.of(User::class.java)
            .create()

        fun build(id: String = user.id , name: String = user.name, surname: String = user.surname) = User(id, name, surname)
    }
}