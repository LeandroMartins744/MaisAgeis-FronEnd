package com.maisageis.ocorrencias.di

import com.maisageis.ocorrencias.ui.login.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ComponentA()
class ComponentB(val componentA : ComponentA)

val moduleItem = module {
    single { ComponentA() }
    single { ComponentB(get()) }

    single { LoginViewModel() }
}

val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build CountriesViewModel
    viewModel {
        LoginViewModel()
    }

}

/*‍
val moduleA = module {
    // Singleton ComponentA
  //  single { ComponentA() }
   // single { ComponentB(get()) }
}


val moduleB = module {
    // Singleton ComponentB with linked instance ComponentA
    //single { ComponentB(get()) }
    ‍
} */