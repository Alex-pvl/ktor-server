package ru.playzone.dto

import kotlinx.serialization.Serializable

@Serializable
data class TokenDTO(
    val id: String,
    val login: String,
    val token: String,
)
