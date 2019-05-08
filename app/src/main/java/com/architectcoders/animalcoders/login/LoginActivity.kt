package com.architectcoders.animalcoders.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.data.remote.login.FirebaseLoginServiceImpl
import com.architectcoders.animalcoders.data.repository.LoginRepositoryImpl
import com.architectcoders.animalcoders.main.MainActivity
import com.architectcoders.animalcoders.tools.goToActivity
import com.architectcoders.animalcoders.tools.hide
import com.architectcoders.animalcoders.tools.show
import com.architectcoders.domain.interactors.LoginInteractor
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginView {

    private val presenter = LoginPresenter(this, LoginInteractor(LoginRepositoryImpl(FirebaseLoginServiceImpl())))

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListeners()
    }

    override fun onClick(view: View) {

        when (view.id) {

            R.id.bt_cancel -> finish()

            R.id.bt_login -> validateCredentials()

            else -> {
            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        pb_wait.show()
    }

    override fun hideProgress() {
        pb_wait.hide()
    }

    override fun clearUsernameError() {
        til_username.error = null
    }

    override fun clearPasswordError() {
        til_password.error = null
    }

    override fun setUsernameError() {
        til_username.error = getString(R.string.username_error)
    }

    override fun setPasswordError() {
        til_password.error = getString(R.string.password_error)
    }

    override fun navigateToHome() {
        pb_wait.hide()
        goToActivity<MainActivity>()
    }

    // -----------------------------------------------------------------------------------------------------------------

    private fun setListeners() {
        bt_login.setOnClickListener(this)
        bt_cancel.setOnClickListener(this)
    }

    private fun validateCredentials() {
        presenter.validateCredentials(tie_username.text.toString(), tie_password.text.toString())
    }
}
