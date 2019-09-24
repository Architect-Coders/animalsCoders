package com.architectcoders.domain.model

enum class Gender {
    FEMALE, MALE, UNKNOWN
}

data class Animal(
    val id: String,
    val name: String,
    val pictureUrl: String,
    val gender: Gender,
    val age: String,
    val description: String)