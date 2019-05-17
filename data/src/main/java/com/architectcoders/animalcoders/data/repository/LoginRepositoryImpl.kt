package com.architectcoders.animalcoders.data.repository

import com.architectcoders.animalcoders.data.model.mapper
import com.architectcoders.animalcoders.data.remote.login.LoginService
import com.architectcoders.domain.repository.LoginRepository

class LoginRepositoryImpl(private val loginService: LoginService) : LoginRepository {

    override suspend fun login(username: String, password: String) = loginService.login(username, password).mapper()

}