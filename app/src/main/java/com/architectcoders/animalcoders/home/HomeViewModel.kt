package com.architectcoders.animalcoders.home

import androidx.lifecycle.ViewModel
import com.architectcoders.domain.interactors.AnimalsInteractor
import com.example.baseandroid.scope.Scope

class HomeViewModel(private val animalsInteractor: AnimalsInteractor)  : ViewModel(), Scope by Scope.Impl