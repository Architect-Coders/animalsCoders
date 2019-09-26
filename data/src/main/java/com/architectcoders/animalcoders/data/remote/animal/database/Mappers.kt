package com.architectcoders.animalcoders.data.remote.animal.database

import com.architectcoders.domain.model.Gender
import com.architectcoders.animalcoders.data.model.Animal as RoomAnimal
import com.architectcoders.domain.model.Animal as DomainAnimal

fun DomainAnimal.toRoomAnimal(): RoomAnimal =
    RoomAnimal(
        id,
        name,
        pictureUrl,
        gender.name,
        age,
        description
    )

fun RoomAnimal.toDomainAnimal(): DomainAnimal =
    DomainAnimal(
        id,
        name,
        pictureUrl,
        mapSex(gender),
        age,
        description
    )

fun mapSex(sex: String): Gender {
    return when (sex) {
        "Male" -> Gender.MALE
        "Female" -> Gender.FEMALE
        else -> Gender.UNKNOWN
    }
}