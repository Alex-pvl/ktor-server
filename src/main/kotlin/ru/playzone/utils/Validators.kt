package ru.playzone.utils

import ru.playzone.models.Token

fun String.isValidEmail() = this.matches(Regex(
    "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
))

fun String.isValidToken() =
    Token.getAll().firstOrNull { it.token == this } != null

fun String.isAdminToken() = this == "b1594f05-2f40-426d-9a74-152040829c33"