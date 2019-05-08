package com.architectcoders.animalcoders.data.remote.login

import com.architectcoders.domain.repository.LoginRepository

interface LoginService {
    suspend fun login(username: String?, password: String?, onLoginRepositoryListener: LoginRepository.OnLoginRepositoryListener?)
}