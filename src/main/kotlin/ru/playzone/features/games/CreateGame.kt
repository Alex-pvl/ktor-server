package ru.playzone.features.games

import kotlinx.serialization.Serializable

@Serializable
data class CreateGameRequest(
    val title: String,
    val description: String,
    val version: String,
    val size: Int,
)

@Serializable
data class CreateGameResponse(
    val gameId: String,
    val title: String,
    val description: String,
    val version: String,
    val size: Int,
)
