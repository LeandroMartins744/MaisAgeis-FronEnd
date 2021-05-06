package com.maisageis.ocorrencias.di.module

import com.maisageis.ocorrencias.ui.login.LoginViewModel
//import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.dsl.module

val viewModelModule = module {
    single {
       LoginViewModel(get())
    }
}