package com.maisageis.ocorrencias.di.module

import com.maisageis.ocorrencias.repository.LoginRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        LoginRepository(get())
    }
}