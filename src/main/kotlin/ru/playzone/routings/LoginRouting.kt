package ru.playzone.routings

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.playzone.controllers.LoginController

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            LoginController(call).login()
            return@post
        }
    }
}