package com.architectcoders.animalcoders.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class LoginViewState : Parcelable {
    @Parcelize
    object EmptyFields : LoginViewState()

    @Parcelize
    object Loading : LoginViewState()

    @Parcelize
    object UsernameError : LoginViewState()

    @Parcelize
    object PasswordError : LoginViewState()

    @Parcelize
    class Error(val errorMessage: String?) : LoginViewState()
}