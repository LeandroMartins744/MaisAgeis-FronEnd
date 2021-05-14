package com.maisageis.ocorrencias.ui.password

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.maisageis.ocorrencias.R

class PasswordActivity: AppCompatActivity() {

    private lateinit var btnforgotpassord: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        initViews()
        initSetOnClicks()
    }

    private fun initSetOnClicks() {
        btnforgotpassord.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initViews() {
        btnforgotpassord = findViewById(R.id.btnforgotpassord)
    }
}