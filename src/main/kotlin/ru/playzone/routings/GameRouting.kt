package ru.playzone.routings

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.playzone.controllers.GameController

fun Application.configureGameRouting() {
    routing {
        post("/games/all") {
            GameController(call).getAll()
            return@post
        }

        post("/games") {
            GameController(call).add()
            return@post
        }
    }
}