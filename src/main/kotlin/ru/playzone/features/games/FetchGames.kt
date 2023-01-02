package ru.playzone.features.games

import kotlinx.serialization.Serializable

@Serializable
class FetchGamesRequest(
    val searchQuery: String,
)