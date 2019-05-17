package com.architectcoders.animalcoders.data.remote.login

import com.architectcoders.animalcoders.data.model.FirebaseResponse

interface LoginService {
    suspend fun login(username: String, password: String): FirebaseResponse
}