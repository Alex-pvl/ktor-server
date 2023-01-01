package ru.playzone.routings

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.playzone.controllers.LoginController

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.login()
            return@post
        }
    }
}