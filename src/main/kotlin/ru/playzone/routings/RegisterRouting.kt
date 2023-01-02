package ru.playzone.routings

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.playzone.controllers.RegisterController

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            RegisterController(call).register()
            return@post
        }
    }
}