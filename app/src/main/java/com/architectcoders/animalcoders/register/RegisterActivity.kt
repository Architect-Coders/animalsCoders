package com.architectcoders.animalcoders.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.animalcoders.R
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val registerViewModel: RegisterViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_cancel.setOnClickListener { finish() }
        btn_signup.setOnClickListener { registerViewModel.validate(tiet_email.text.toString(), tiet_password.text.toString()) }
    }
}
