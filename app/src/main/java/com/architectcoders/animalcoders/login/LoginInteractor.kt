package com.architectcoders.animalcoders.login

import com.architectcoders.animalcoders.tools.postDelayed

class LoginInteractor {

    interface OnLoginFinishedListener {
        fun onUsernameError()
        fun onPasswordError()
        fun onUsernameSuccess()
        fun onPasswordSuccess()
        fun onSuccess()
    }

    fun login (username: String, password: String, listener: OnLoginFinishedListener) {

        postDelayed(2000) {
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
        }
    }
}