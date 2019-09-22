package com.example.baseandroid.coroutines

import kotlinx.coroutines.CoroutineDispatcher

class CoroutineDispatchers(
    val uiDispatcher: CoroutineDispatcher,
    val defaultDispatcher: CoroutineDispatcher
)