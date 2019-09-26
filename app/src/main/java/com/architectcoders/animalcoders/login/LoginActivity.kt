package com.architectcoders.animalcoders.login

import android.view.View
import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.main.MainActivity
import com.example.baseandroid.activity.BaseActivity
import com.example.baseandroid.click.setSafeOnClickListener
import com.example.baseandroid.extensions.goToActivity
import com.example.baseandroid.extensions.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<LoginViewState, LoginViewTransition, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModel()

    override fun getLayout(): Int = R.layout.activity_login

    override fun initView() {
        //view configurations, adapter initialization
    }

    override fun manageTransition(transition: LoginViewTransition) {
        when (transition) {
            is LoginViewTransition.NavigateToHome -> {
                hideKeyboard()
                goToActivity<MainActivity>()
            }
        }
    }

    override fun manageState(state: LoginViewState) {
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

    override fun initListeners() {
        bt_login.setSafeOnClickListener {
            validateCredentials()
        }
        bt_cancel.setSafeOnClickListener {
            cancelForm()
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
