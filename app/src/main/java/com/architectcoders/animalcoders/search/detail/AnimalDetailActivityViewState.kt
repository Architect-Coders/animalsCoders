package com.architectcoders.animalcoders.search.detail

import android.os.Parcelable
import com.architectcoders.domain.model.Animal
import kotlinx.android.parcel.Parcelize

sealed class AnimalDetailActivityViewState : Parcelable {
    @Parcelize
    class ViewLoaded(val animal: Animal?): AnimalDetailActivityViewState()
    @Parcelize
    class FavouriteModified(val favourite: Boolean) : AnimalDetailActivityViewState()
}