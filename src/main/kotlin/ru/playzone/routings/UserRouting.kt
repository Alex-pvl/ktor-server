package ru.playzone.routings

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.playzone.controllers.UserController

fun Application.configureUserRouting() {
    routing {
        get("/users") {
            UserController(call).getAll()
            return@get
        }
    }
}