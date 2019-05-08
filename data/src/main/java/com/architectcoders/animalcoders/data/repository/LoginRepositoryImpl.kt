package com.architectcoders.animalcoders.data.repository

import com.architectcoders.animalcoders.data.remote.login.LoginService
import com.architectcoders.domain.repository.LoginRepository

class LoginRepositoryImpl (private val loginService: LoginService) : LoginRepository {

    override suspend fun login(username: String?, password: String?, onLoginRepositoryListener: LoginRepository.OnLoginRepositoryListener?) {
        loginService.login(username,password, object: LoginRepository.OnLoginRepositoryListener {
            override fun onError() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        } )
    }
}