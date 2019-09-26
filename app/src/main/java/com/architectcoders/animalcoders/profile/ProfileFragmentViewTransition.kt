package com.architectcoders.animalcoders.profile


sealed class ProfileFragmentViewTransition {
    object NavigateToFavourites : ProfileFragmentViewTransition()
    object NavigateToLogin : ProfileFragmentViewTransition()
}