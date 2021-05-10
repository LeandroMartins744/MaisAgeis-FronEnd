package com.maisageis.ocorrencias.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.maisageis.ocorrencias.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var btnRegister: Button

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        initViews()
        initSetOnClicks()
    }

    private fun initSetOnClicks() {
        btnRegister.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initViews() {
        btnRegister = findViewById(R.id.btnCadastrar)
    }
}