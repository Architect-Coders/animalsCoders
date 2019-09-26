package com.architectcoders.animalcoders.data.model

import com.architectcoders.domain.model.Gender
import com.architectcoders.domain.model.Animal as DomainAnimal

data class AnimalResponse (
    val data: List<Animal>?
) {

    data class Animal(
        val id: String?,
        val attributes: Attributes?
    ) {

        data class Attributes(
            val name: String?,
            val pictureThumbnailUrl: String?,
            val ageString: String?,
            val descriptionText: String?,
            val sex: String?) {


            fun mapSex(): Gender {
                return when (sex) {
                    "Male" -> Gender.MALE
                    "Female" -> Gender.FEMALE
                    else -> Gender.UNKNOWN
                }
            }

        }


        fun map() = DomainAnimal(
                id ?: "",
                attributes?.name ?: "",
                attributes?.pictureThumbnailUrl ?: "",
                attributes?.mapSex() ?: Gender.UNKNOWN,
                attributes?.ageString ?: "",
                attributes?.descriptionText ?: ""
            )

    }

    fun map() = data?.map { it.map() } ?: listOf()

}