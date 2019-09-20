package com.architectcoders.animalcoders.register

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.main.MainActivity
import com.architectcoders.animalcoders.tools.goToActivity
import com.architectcoders.animalcoders.tools.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val registerViewModel: RegisterViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_cancel.setOnClickListener {
            hideKeyboard()
            finish()
        }
        btn_signup.setOnClickListener {
            hideKeyboard()

            registerViewModel.validate(
                tiet_email.text.toString(),
                tiet_password.text.toString()
            )
        }

        registerViewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: RegisterViewModel.UiModel) {
        loading_content.visibility =
            if (model is RegisterViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is RegisterViewModel.UiModel.Content -> goToActivity<MainActivity>()
            is RegisterViewModel.UiModel.Error.UNKNOW -> Snackbar.make(
                loading_content,
                R.string.unknown_error,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
