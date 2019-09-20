package com.architectcoders.animalcoders

import android.app.Application
import com.architectcoders.animalcoders.data.remote.login.FirebaseLoginServiceImpl
import com.architectcoders.animalcoders.data.remote.login.LoginService
import com.architectcoders.animalcoders.data.repository.LoginRepositoryImpl
import com.architectcoders.animalcoders.home.HomeActivity
import com.architectcoders.animalcoders.home.HomeViewModel
import com.architectcoders.animalcoders.login.LoginActivity
import com.architectcoders.animalcoders.login.LoginViewModel
import com.architectcoders.animalcoders.register.RegisterActivity
import com.architectcoders.animalcoders.register.RegisterViewModel
import com.architectcoders.domain.interactors.LoginInteractor
import com.architectcoders.domain.interactors.RegisterInteractor
import com.architectcoders.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {

}

private val dataModule = module {
    factory<LoginService> { FirebaseLoginServiceImpl(FirebaseAuth.getInstance()) }
    factory<AuthRepository> { LoginRepositoryImpl(loginService = get()) }
}

private val scopesModule = module {
    scope(named<LoginActivity>()) {
        viewModel { LoginViewModel(interactor = get()) }
        scoped { LoginInteractor(authRepository = get()) }
    }

    scope(named<HomeActivity>()) {
        viewModel { HomeViewModel(animalsInteractor = get()) }
        //scoped { AnimalsInteractor(animalsRepository = get()) }
    }
    scope(named<RegisterActivity>()) {
        viewModel { RegisterViewModel(get()) }
        scoped { RegisterInteractor(get()) }
    }

}
