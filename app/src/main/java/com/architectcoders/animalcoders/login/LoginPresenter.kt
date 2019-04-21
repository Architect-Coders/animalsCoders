package com.architectcoders.animalcoders.login

class LoginPresenter(var loginView: LoginView?, val loginInteractor: LoginInteractor) :
    LoginInteractor.OnLoginFinishedListener {

    override fun onUsernameSuccess() {
        loginView?.apply {
            clearUsernameError()
        }
    }

    override fun onUsernameError() {
        loginView?.apply {
            setUsernameError()
            hideProgress()
        }
    }

    override fun onPasswordSuccess() {
        loginView?.apply {
            clearPasswordError()
        }
    }

    override fun onPasswordError() {
        loginView?.apply {
            setPasswordError()
            hideProgress()
        }
    }

    override fun onSuccess() {
        loginView?.navigateToHome()
    }

    // -----------------------------------------------------------------------------------------------------------------

    fun validateCredentials(username: String, password: String) {
        loginView?.showProgress()
        loginInteractor.login(username, password, this)
    }

    fun onDestroy() {
        loginView = null
    }
}