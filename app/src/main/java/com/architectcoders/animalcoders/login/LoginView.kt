package com.architectcoders.animalcoders.login

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun clearUsernameError()
    fun setUsernameError()
    fun clearPasswordError()
    fun setPasswordError()
    fun navigateToHome()
}