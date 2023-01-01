package ru.playzone.utils

fun String.isValidEmail() = this.matches(Regex(
    "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
))