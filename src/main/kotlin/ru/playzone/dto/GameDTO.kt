package ru.playzone.dto

import kotlinx.serialization.Serializable
import ru.playzone.features.games.CreateGameRequest
import ru.playzone.features.games.CreateGameResponse
import java.util.UUID

@Serializable
data class GameDTO(
    val gameId: String,
    val title: String,
    val logo: String,
    val description: String,
    val version: String,
    val weight: Int,
    val downloadCount: Int,
)

fun CreateGameRequest.toDTO() =
    GameDTO(
        gameId = UUID.randomUUID().toString(),
        title = title,
        logo = "",
        description = description,
        version = version,
        weight = size,
        downloadCount = 0,
    )

fun GameDTO.toResponse() =
    CreateGameResponse(
        gameId = gameId,
        title = title,
        description = description,
        version = version,
        size = weight,
    )