package com.maisageis.ocorrencias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maisageis.ocorrencias.di.ComponentB
import com.maisageis.ocorrencias.ui.main.MainFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}