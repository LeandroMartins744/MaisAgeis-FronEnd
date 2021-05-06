package com.maisageis.ocorrencias

import android.app.Application
import com.maisageis.ocorrencias.di.module.appModule
import com.maisageis.ocorrencias.di.module.repoModule
import com.maisageis.ocorrencias.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}