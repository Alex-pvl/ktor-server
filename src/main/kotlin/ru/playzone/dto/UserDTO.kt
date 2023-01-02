package ru.playzone.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val login: String,
    val password: String,
    val username: String,
    val email: String,
)
