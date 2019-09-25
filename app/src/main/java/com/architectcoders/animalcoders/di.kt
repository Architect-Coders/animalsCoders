package com.architectcoders.animalcoders

import android.app.Application
import com.architectcoders.animalcoders.data.BuildConfig
import com.architectcoders.animalcoders.data.remote.animal.AnimalsService
import com.architectcoders.animalcoders.data.remote.animal.AnimalsServiceImpl
import com.architectcoders.animalcoders.data.remote.auth.AuthService
import com.architectcoders.animalcoders.data.remote.auth.FirebaseAuthServiceImpl
import com.architectcoders.animalcoders.data.remote.login.FirebaseLoginServiceImpl
import com.architectcoders.animalcoders.data.remote.login.LoginService
import com.architectcoders.animalcoders.data.repository.AnimalsRepositoryImpl
import com.architectcoders.animalcoders.data.repository.AuthRepositoryImpl
import com.architectcoders.animalcoders.login.LoginViewModel
import com.architectcoders.animalcoders.main.MainActivityViewModel
import com.architectcoders.animalcoders.map.MapFragmentViewModel
import com.architectcoders.animalcoders.profile.ProfileFragmentViewModel
import com.architectcoders.animalcoders.search.SearchFragmentViewModel
import com.architectcoders.animalcoders.search.detail.AnimalDetailActivityViewModel
import com.architectcoders.domain.interactors.AnimalsInteractor
import com.architectcoders.domain.interactors.AuthInteractor
import com.architectcoders.domain.interactors.LoginInteractor
import com.architectcoders.domain.model.Animal
import com.architectcoders.domain.repository.AnimalsRepository
import com.architectcoders.domain.repository.AuthRepository
import com.example.baseandroid.di.baseModule
import com.google.firebase.auth.FirebaseAuth
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(baseModule, appModule, domainModule, dataModule))
    }
}

private val appModule = module {
    viewModel { LoginViewModel(interactor = get(), dispatchers = get()) }
    viewModel { MainActivityViewModel(authInteractor = get(), dispatchers = get()) }
    viewModel { SearchFragmentViewModel(interactor = get(), dispatchers = get()) }
    viewModel { ProfileFragmentViewModel(dispatchers = get()) }
    viewModel { MapFragmentViewModel(dispatchers = get()) }
    viewModel { (animal: Animal?) ->
        AnimalDetailActivityViewModel(
            animal = animal,
            dispatchers = get()
        )
    }
}

private val domainModule = module {
    factory { LoginInteractor(authRepository = get()) }
    factory { AnimalsInteractor(animalsRepository = get()) }
    factory { AuthInteractor(authRepository = get()) }
}

private val dataModule = module {

    single {

        val client = OkHttpClient().newBuilder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }).addInterceptor { chain ->

                val builder = Headers.Builder()
                builder.add("Accept", "application/json")
                builder.add("Authorization", androidApplication().getString(R.string.api_key))

                val headers = builder.build()

                val request = chain.request().newBuilder().headers(headers).build()
                chain.proceed(request)
            }
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(androidApplication().getString(R.string.api_url))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())

        retrofitBuilder.build()
    }

    factory<AuthService> { FirebaseAuthServiceImpl(FirebaseAuth.getInstance()) }
    factory<LoginService> { FirebaseLoginServiceImpl(FirebaseAuth.getInstance()) }
    factory<AuthRepository> { AuthRepositoryImpl(loginService = get(), authService = get()) }
    factory<AnimalsService> { AnimalsServiceImpl(retrofit = get()) }
    factory<AnimalsRepository> { AnimalsRepositoryImpl(animalsService = get()) }
}
