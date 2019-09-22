package com.architectcoders.animalcoders.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class MainActivityViewState : Parcelable {
    @Parcelize
    object InitialState: MainActivityViewState()
}