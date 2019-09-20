package com.architectcoders.animalcoders.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.main.MainActivity
import com.architectcoders.animalcoders.register.RegisterActivity
import com.architectcoders.animalcoders.tools.goToActivity
import com.architectcoders.animalcoders.tools.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: LoginViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListeners()
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun setListeners() {
        bt_login.setOnClickListener(this)
        bt_cancel.setOnClickListener(this)
        bt_register.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        return when (view.id) {

            R.id.bt_cancel -> cancelForm()

            R.id.bt_login -> validateCredentials()

            R.id.bt_register -> goToActivity<RegisterActivity>(false)

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
        Snackbar.make(
            rl_login_container,
            state.errorMessage ?: getString(R.string.unknown_error),
            Snackbar.LENGTH_LONG
        )
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
