package ru.playzone.dto

import java.util.UUID

data class TokenDTO(
    val id: UUID,
    val login: String,
    val token: UUID,
)
