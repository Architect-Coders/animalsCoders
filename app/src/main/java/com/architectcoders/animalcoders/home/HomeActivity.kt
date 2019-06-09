package com.architectcoders.animalcoders.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.data.repository.AnimalsRepositoryImpl
import com.architectcoders.animalcoders.tools.getViewModel
import com.architectcoders.domain.interactors.AnimalsInteractor

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        injection()
    }

    //TODO: Hacerlo con Dagger o Koin
    private fun injection() {
        val animalsInteractor = AnimalsInteractor(AnimalsRepositoryImpl())
        viewModel = getViewModel { HomeViewModel(animalsInteractor) }
    }
}