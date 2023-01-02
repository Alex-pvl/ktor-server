package ru.playzone.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.playzone.dto.TokenDTO
import ru.playzone.dto.UserDTO
import ru.playzone.features.register.RegisterReceive
import ru.playzone.features.register.RegisterResponse
import ru.playzone.models.Token
import ru.playzone.models.User
import ru.playzone.models.User.getOneByLogin
import ru.playzone.utils.isValidEmail
import java.util.*

class RegisterController(
    private val call: ApplicationCall,
) {

    suspend fun register() {
        val receive = call.receive<RegisterReceive>()

        if (!receive.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Invalid email")
            return
        }

        val user = getOneByLogin(receive.login)

        user?.let {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        }

        val token = UUID.randomUUID().toString()

        try {
            User.create(
                UserDTO(
                    login = receive.login,
                    password = receive.password,
                    username = "",
                    email = receive.email,
                )
            )
        } catch (e: ExposedSQLException) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Can't create user ${e.localizedMessage}")
        }

        Token.create(
            TokenDTO(
                id = UUID.randomUUID().toString(),
                login = receive.login,
                token = token,
            )
        )

        call.respond(RegisterResponse(token = token))
    }
}