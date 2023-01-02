package ru.playzone.controllers

import io.ktor.server.application.*
import io.ktor.server.response.*
import ru.playzone.models.User

class UserController(
    private val call: ApplicationCall,
) {
    suspend fun getAll() {
        val users = User.getAll()
        call.respond(users)
    }
}