package ru.playzone.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.playzone.dto.toDTO
import ru.playzone.dto.toResponse
import ru.playzone.features.games.CreateGameRequest
import ru.playzone.features.games.FetchGamesRequest
import ru.playzone.models.Game
import ru.playzone.utils.isAdminToken
import ru.playzone.utils.isValidToken

data class GameController(
    private val call: ApplicationCall,
) {
    suspend fun getAll() {
        val request = call.receive<FetchGamesRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        if (token.orEmpty().isValidToken() || token.orEmpty().isAdminToken()) {
            if (request.searchQuery.isBlank()) {
                call.respond(Game.getAll())
            } else {
                call.respond(
                    Game.getAll().filter {
                        it.title.contains(request.searchQuery, ignoreCase = true)
                    }
                )
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    suspend fun add() {
        val token = call.request.headers["Bearer-Authorization"]
        if (token.orEmpty().isAdminToken()) {
            val request = call.receive<CreateGameRequest>()
            val game = request.toDTO()
            Game.create(game)
            call.respond(game.toResponse())
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }
}
