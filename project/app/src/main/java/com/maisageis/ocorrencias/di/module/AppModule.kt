package com.maisageis.ocorrencias.di.module

import android.content.Context
import android.os.Build
import com.google.gson.GsonBuilder
import com.maisageis.ocorrencias.data.util.*
import com.maisageis.ocorrencias.repository.LoginRepository
import com.maisageis.ocorrencias.ui.login.LoginViewModel
import com.maisageis.ocorrencias.util.Security
import com.maisageis.ocorrencias.util.SecurityData
import okhttp3.OkHttpClient
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor("maisageis", "123@"))
        .build()

private val gson = GsonBuilder()
        .setLenient()
        .create()

private fun providerRetrofit() = Retrofit.Builder()
        .baseUrl("http://www.maisageis.kinghost.net/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


private fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

val appModule = module() {
    single { providerRetrofit() }
    single<ApiHelper> { ApiHelperImpl()}
    single { provideApiService(get()) }
}

val repoModule = module {
    single { LoginRepository(get()) }
    single { Security(androidApplication()) }
    single { SecurityData(get()) }
}

val viewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
}