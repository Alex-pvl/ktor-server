package ru.playzone.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.playzone.dto.TokenDTO
import ru.playzone.features.login.LoginReceive
import ru.playzone.features.login.LoginResponse
import ru.playzone.models.Token
import ru.playzone.models.User
import java.util.*

class LoginController(
    private val call: ApplicationCall,
) {
    suspend fun login() {
        val receive = call.receive<LoginReceive>()
        val user = User.getByLogin(receive.login)

        user?.let {
            if (it.password == receive.password) {
                val token = UUID.randomUUID()

                Token.create(
                    TokenDTO(
                        id = UUID.randomUUID(),
                        login = receive.login,
                        token = token,
                    )
                )
                call.respond(LoginResponse(token = token.toString()))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }

        call.respond(HttpStatusCode.NotFound, "User not fount")
    }
}