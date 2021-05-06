package com.maisageis.ocorrencias

import android.app.Application
import com.maisageis.ocorrencias.di.moduleItem
import com.maisageis.ocorrencias.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class App: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(koinModules())
        }
    }

    private fun koinModules(): List<Module>{
        return listOf(
            moduleItem,
            viewModelModule
        )
    }
}