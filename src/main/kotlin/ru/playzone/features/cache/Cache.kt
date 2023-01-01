package ru.playzone.features.cache

import ru.playzone.features.register.RegisterReceive

data class TokenCache(
    val login: String,
    val token: String,
)

object Cache {
    val users = mutableListOf<RegisterReceive>()
    val token = mutableListOf<TokenCache>()
}