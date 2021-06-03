package com.maisageis.ocorrencias

import android.app.Application
import com.maisageis.ocorrencias.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    repoModule,
                    viewModelLoginModule,
                    viewModelRegisterModule,
                    viewModelHomeModule,
                    viewModelMyDataModule,
                    viewModelDetailsModule,
                    viewModelMapsModule,
                    viewModelLocationModule)
            )
        }
    }
}