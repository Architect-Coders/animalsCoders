package com.architectcoders.animalcoders.main

import com.architectcoders.domain.model.Animal

sealed class MainActivityViewTransition {
    object NavigateToLogin : MainActivityViewTransition()
    object NavigateToProfile : MainActivityViewTransition()
    object NavigateToMap : MainActivityViewTransition()
    object NavigateToSearch : MainActivityViewTransition()
    class NavigateToAnimalDetail(val animal: Animal) : MainActivityViewTransition()
}