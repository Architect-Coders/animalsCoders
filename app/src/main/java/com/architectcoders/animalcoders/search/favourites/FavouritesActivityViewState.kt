package com.architectcoders.animalcoders.search.favourites

import android.os.Parcelable
import com.architectcoders.domain.model.Animal
import kotlinx.android.parcel.Parcelize

sealed class FavouritesActivityViewState : Parcelable {
    @Parcelize
    class FavouritesLoaded(val animals: List<Animal>?): FavouritesActivityViewState()
}