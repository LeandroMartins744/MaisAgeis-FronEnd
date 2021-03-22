package com.maisageis.ocorrencias.ui.cadastro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.maisageis.ocorrencias.R

class CadastroActivity : AppCompatActivity() {

    private lateinit var btnCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        initViews()
        initSetOnClicks()
    }

    private fun initSetOnClicks() {
        btnCadastrar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initViews() {
        btnCadastrar = findViewById(R.id.btnCadastrar)
    }
}