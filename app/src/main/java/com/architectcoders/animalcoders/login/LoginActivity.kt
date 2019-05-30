package com.architectcoders.animalcoders.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.data.remote.login.FirebaseLoginServiceImpl
import com.architectcoders.animalcoders.data.repository.LoginRepositoryImpl
import com.architectcoders.animalcoders.main.MainActivity
import com.architectcoders.animalcoders.tools.getViewModel
import com.architectcoders.animalcoders.tools.goToActivity
import com.architectcoders.animalcoders.tools.hideKeyboard
import com.architectcoders.domain.interactors.LoginInteractor
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        injection()
        setListeners()
        viewModel.model.observe(this, Observer(::updateUi))
    }

    //TODO: Hacerlo con Dagger o Koin
    private fun injection() {
        val loginService = FirebaseLoginServiceImpl(FirebaseAuth.getInstance())
        val loginRepository = LoginRepositoryImpl(loginService)
        val loginInteractor = LoginInteractor(loginRepository)
        viewModel = getViewModel { LoginViewModel(loginInteractor) }
    }

    private fun setListeners() {
        bt_login.setOnClickListener(this)
        bt_cancel.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        when (view.id) {

            R.id.bt_cancel -> cancelForm()

            R.id.bt_login -> validateCredentials()

            else -> {
            }
        }
    }

    private fun cancelForm() {
        viewModel.cancelForm()
    }

    private fun validateCredentials() {
        viewModel.validateCredentials(tie_username.text.toString(), tie_password.text.toString())
    }

    private fun updateUi(model: LoginViewState) {
        pb_wait.visibility = if (model is LoginViewState.Loading) View.VISIBLE else View.GONE

        when (model) {
            is LoginViewState.EmptyFields -> clearForm()
            is LoginViewState.UsernameError -> {
                clearUsernameError()
                setUsernameError()
            }
            is LoginViewState.PasswordError -> {
                clearPasswordError()
                setPasswordError()
            }
            is LoginViewState.Error -> {
                hideKeyboard()
                showError(model)
            }
            is LoginViewState.NavigateToHome -> {
                hideKeyboard()
                goToActivity<MainActivity>()
            }
        }
    }

    private fun clearForm() {
        clearUsername()
        clearUserPassword()
        clearUsernameError()
        clearPasswordError()
    }

    private fun showError(state: LoginViewState.Error) {
        Snackbar.make(rl_login_container, state.errorMessage ?: getString(R.string.unknown_error), Snackbar.LENGTH_LONG)
            .show()
    }

    private fun clearUsername() {
        tie_username.text?.clear()
    }

    private fun clearUserPassword() {
        tie_password.text?.clear()
    }

    private fun clearUsernameError() {
        til_username.error = null
    }

    private fun clearPasswordError() {
        til_password.error = null
    }

    private fun setUsernameError() {
        til_username.error = getString(R.string.username_error)
    }

    private fun setPasswordError() {
        til_password.error = getString(R.string.password_error)
    }
}
