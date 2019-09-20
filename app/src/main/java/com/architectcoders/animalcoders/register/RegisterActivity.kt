package com.architectcoders.animalcoders.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.animalcoders.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_cancel.setOnClickListener { finish() }
    }
}
