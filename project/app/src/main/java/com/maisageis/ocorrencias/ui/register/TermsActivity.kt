package com.maisageis.ocorrencias.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.ui.login.LoginActivity

class TermsActivity : AppCompatActivity() {
    private lateinit var btnConfirm: Button

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, TermsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        initViews()
        initSetOnClicks()
    }

    private fun initSetOnClicks() {
        btnConfirm.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initViews() {
        btnConfirm = findViewById(R.id.btnConfirm)
    }
}