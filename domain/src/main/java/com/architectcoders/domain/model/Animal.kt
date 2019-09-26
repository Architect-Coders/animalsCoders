package com.architectcoders.domain.model

import java.io.Serializable

enum class Gender {
    FEMALE, MALE, UNKNOWN
}

data class Animal(
    val id: String,
    val name: String,
    val pictureUrl: String,
    val gender: Gender,
    val age: String,
    val description: String,
    val favourite: Boolean = false
) : Serializable