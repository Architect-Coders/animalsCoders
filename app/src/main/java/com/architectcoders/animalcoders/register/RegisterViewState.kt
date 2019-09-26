package com.architectcoders.animalcoders.register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class RegisterViewState : Parcelable {
    @Parcelize
    object Loading : RegisterViewState()
    @Parcelize
    object Error : RegisterViewState()
}