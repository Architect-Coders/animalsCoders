package com.architectcoders.animalcoders.login

import com.architectcoders.animalcoders.tools.Scope
import com.architectcoders.domain.interactors.LoginInteractor
import kotlinx.coroutines.launch

class LoginPresenter(var loginView: LoginView?, val loginInteractor: LoginInteractor) :
    LoginInteractor.OnLoginFinishedListener, Scope by Scope.Impl {

    init {
        initScope()
    }

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
        launch {
            loginView?.showProgress()
            //loginInteractor.login(username, password, this@LoginPresenter)
        }
    }

    fun onDestroy() {
        loginView = null
        destroyScope()
    }
}