package com.architectcoders.animalcoders

import android.app.Application
import com.architectcoders.animalcoders.data.remote.auth.AuthService
import com.architectcoders.animalcoders.data.remote.auth.FirebaseAuthServiceImpl
import com.architectcoders.animalcoders.data.remote.login.FirebaseLoginServiceImpl
import com.architectcoders.animalcoders.data.remote.login.LoginService
import com.architectcoders.animalcoders.data.repository.AuthRepositoryImpl
import com.architectcoders.animalcoders.home.HomeViewModel
import com.architectcoders.animalcoders.login.LoginViewModel
import com.architectcoders.animalcoders.main.MainActivityViewModel
import com.architectcoders.domain.interactors.AnimalsInteractor
import com.architectcoders.domain.interactors.AuthInteractor
import com.architectcoders.domain.interactors.LoginInteractor
import com.architectcoders.domain.repository.AuthRepository
import com.example.baseandroid.di.baseModule
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(baseModule, appModule, domainModule, dataModule))
    }
}

private val appModule = module {

    viewModel { LoginViewModel(interactor = get(), dispatchers = get()) }

    viewModel { HomeViewModel(animalsInteractor = get()) }

    viewModel { MainActivityViewModel(authInteractor = get(), dispatchers = get()) }
}

private val domainModule = module {
    factory { LoginInteractor(authRepository = get()) }
    factory { AnimalsInteractor(animalsRepository = get()) }
    factory { AuthInteractor(authRepository = get()) }
}

private val dataModule = module {
    factory<AuthService> { FirebaseAuthServiceImpl(FirebaseAuth.getInstance()) }
    factory<LoginService> { FirebaseLoginServiceImpl(FirebaseAuth.getInstance()) }
    factory<AuthRepository> { AuthRepositoryImpl(loginService = get(), authService = get()) }
}
