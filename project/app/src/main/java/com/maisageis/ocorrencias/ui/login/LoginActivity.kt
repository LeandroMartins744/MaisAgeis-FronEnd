package com.maisageis.ocorrencias.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.textview.MaterialTextView
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.ui.cadastro.CadastroActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var labelCadastro: MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        initOnClicks()
    }

    private fun initOnClicks() {
        labelCadastro.setOnClickListener {
            val intent = Intent(
                this@LoginActivity, CadastroActivity::class.java
            )
            startActivity(intent)
        }
    }

    private fun initViews() {
        labelCadastro = findViewById(R.id.lblCadastro)
    }

}