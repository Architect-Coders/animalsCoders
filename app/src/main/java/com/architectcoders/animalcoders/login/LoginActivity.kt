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
        val loginService = FirebaseLoginServiceImpl()
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
            is LoginViewState.ClearPassword -> clearPasswordError()
            is LoginViewState.ClearUsername -> clearUsernameError()
            is LoginViewState.UsernameError -> setUsernameError()
            is LoginViewState.PasswordError -> setPasswordError()
            is LoginViewState.NavigateToHome -> {
                hideKeyboard()
                goToActivity<MainActivity>()
            }
        }
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
