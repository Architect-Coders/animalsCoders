package com.architectcoders.domain.interactors

class LoginInteractor(val loginRepository: LoginRepository) {

    interface OnLoginFinishedListener {
        fun onUsernameError()
        fun onPasswordError()
        fun onUsernameSuccess()
        fun onPasswordSuccess()
        fun onSuccess()
    }

    suspend fun login (username: String, password: String, listener: OnLoginFinishedListener) {

        //postDelayed(2000) {
            var isOK = true

            if (username.isEmpty()) {
                isOK = false
                listener.onUsernameError()
            } else {
                listener.onUsernameSuccess()
            }

            if (password.isEmpty()) {
                isOK = false
                listener.onPasswordError()
            } else {
                listener.onPasswordSuccess()
            }

            if (isOK) {
                listener.onSuccess()
            }
        //}
    }
}