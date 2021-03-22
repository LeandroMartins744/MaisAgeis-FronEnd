package com.maisageis.ocorrencias.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.maisageis.ocorrencias.MainActivity
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.ui.login.LoginActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed(Runnable { mostrarMainActivity() }, 3000)
    }
    private fun mostrarMainActivity() {
        val intent = Intent(
            this@SplashActivity, LoginActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}