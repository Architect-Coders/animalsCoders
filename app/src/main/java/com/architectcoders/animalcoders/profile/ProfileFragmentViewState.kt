package com.architectcoders.animalcoders.profile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class ProfileFragmentViewState : Parcelable {
    @Parcelize
    class UserLoaded(val userName: String): ProfileFragmentViewState()
}
