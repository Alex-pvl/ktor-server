package ru.playzone

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import ru.playzone.plugins.*
import ru.playzone.routings.configureLoginRouting
import ru.playzone.routings.configureRegisterRouting

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    Database.connect(
        url = "jdbc:postgresql://localhost:PORT/playzone",
        driver = "org.postgresql.Driver",
        user = "**SECRET**",
        password = "**SECRET**"
    )

    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureSerialization()
}
