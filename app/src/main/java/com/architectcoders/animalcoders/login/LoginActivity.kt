package com.architectcoders.animalcoders.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.main.MainActivity
import com.example.baseandroid.extensions.goToActivity
import com.example.baseandroid.extensions.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListeners()
        viewModel.getViewTransition().observe(this, Observer {
            manageTransitions(it)
        })
        viewModel.getViewState().observe(this, Observer {
            manageState(it)
        })
        viewModel.initView()
    }

    private fun manageTransitions(transition: LoginViewTransition) {
        when (transition) {
            is LoginViewTransition.NavigateToHome -> {
                hideKeyboard()
                goToActivity<MainActivity>()
            }
        }
    }

    private fun manageState(state: LoginViewState) {
        pb_wait.visibility = if (state is LoginViewState.Loading) View.VISIBLE else View.GONE

        when (state) {
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
                showError(state)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        clearObservers()
    }

    private fun clearObservers() {
        viewModel.getViewState().removeObservers(this)
        viewModel.getViewTransition().removeObservers(this)
    }

    private fun setListeners() {
        bt_login.setOnClickListener(this)
        bt_cancel.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        return when (view.id) {

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
