package com.architectcoders.animalcoders.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class MainActivityViewState : Parcelable {
    @Parcelize
    object SearchState: MainActivityViewState()
    @Parcelize
    object MapState: MainActivityViewState()
    @Parcelize
    object ProfileState: MainActivityViewState()
    @Parcelize
    object InitialState: MainActivityViewState()
}