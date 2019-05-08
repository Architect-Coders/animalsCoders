package com.architectcoders.domain.repository

interface LoginRepository {

    interface OnLoginRepositoryListener {
        fun onError()
        fun onSuccess()
    }

    suspend fun login(username: String?, password: String?, onLoginRepositoryListener: OnLoginRepositoryListener?)
}
