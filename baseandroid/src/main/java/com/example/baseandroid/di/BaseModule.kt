package com.example.baseandroid.di

import com.example.baseandroid.coroutines.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val baseModule = module {

    single { CoroutineDispatchers(Dispatchers.Main, Dispatchers.Default) }

}