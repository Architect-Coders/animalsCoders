package com.architectcoders.animalcoders.register

import android.view.View
import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.main.MainActivity
import com.example.baseandroid.activity.BaseActivity
import com.example.baseandroid.click.setSafeOnClickListener
import com.example.baseandroid.extensions.goToActivity
import com.example.baseandroid.extensions.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity :
    BaseActivity<RegisterViewState, RegisterViewTransition, RegisterViewModel>() {

    override val viewModel: RegisterViewModel by viewModel()

    override fun getLayout(): Int = R.layout.activity_register

    override fun initView() {

    }

    override fun manageState(state: RegisterViewState) {
        loading_content.visibility =
            if (state is RegisterViewState.Loading) View.VISIBLE else View.GONE
        when (state) {
            is RegisterViewState.Error -> Snackbar.make(
                loading_content,
                R.string.unknown_error,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun manageTransition(transition: RegisterViewTransition) {
        when (transition) {
            RegisterViewTransition.NavigateToHome -> {
                goToActivity<MainActivity>()
            }
        }
    }

    override fun initListeners() {
        btn_cancel.setSafeOnClickListener {
            hideKeyboard()
            finish()
        }
        btn_signup.setSafeOnClickListener {
            hideKeyboard()
            viewModel.validate(
                tiet_email.text.toString(),
                tiet_password.text.toString()
            )
        }
    }
}
