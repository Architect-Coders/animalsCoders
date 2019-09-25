package com.architectcoders.animalcoders.search

import com.architectcoders.domain.model.Animal


sealed class SearchFragmentViewTransition {
    class NavigateToAnimalDetail(val animal: Animal) : SearchFragmentViewTransition()
}