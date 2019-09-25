package com.architectcoders.animalcoders.search

import android.os.Parcelable
import com.architectcoders.domain.model.Animal
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

sealed class SearchFragmentViewState : Parcelable {

    @Parcelize
    data class DrawAnimals(val animals: @RawValue List<Animal>) : SearchFragmentViewState()
}
