package com.maisageis.ocorrencias.di.module

import com.google.gson.GsonBuilder
import com.maisageis.ocorrencias.data.bo.BOApi
import com.maisageis.ocorrencias.data.bo.BOApiImp
import com.maisageis.ocorrencias.data.street.StreetApi
import com.maisageis.ocorrencias.data.street.StreetApiImp
import com.maisageis.ocorrencias.data.user.UserApi
import com.maisageis.ocorrencias.data.user.UserApiImp
import com.maisageis.ocorrencias.data.util.*
import com.maisageis.ocorrencias.repository.BORepository
import com.maisageis.ocorrencias.repository.StreetRepository
import com.maisageis.ocorrencias.repository.UserRepository
import com.maisageis.ocorrencias.ui.home.HomeViewModel
import com.maisageis.ocorrencias.ui.login.LoginViewModel
import com.maisageis.ocorrencias.ui.register.RegisterViewModel
import com.maisageis.ocorrencias.util.Security
import com.maisageis.ocorrencias.util.SecurityData
import okhttp3.OkHttpClient
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
    single<BOApi> { BOApiImp() }
    single<StreetApi> { StreetApiImp() }
    single<UserApi> { UserApiImp() }
    single { provideApiService(get()) }
}

val repoModule = module {
    single { StreetRepository(get()) }
    single { UserRepository(get()) }
    single { BORepository(get()) }
    single { Security(androidApplication()) }
    single { SecurityData(get()) }
}

val viewModelModule = module {
    viewModel {
        LoginViewModel(get())
        RegisterViewModel(get(), get())
    }
}

val viewModelLoginModule = module {
    viewModel {
        LoginViewModel(get())
    }
}

val viewModelRegisterModule = module {
    viewModel {
        RegisterViewModel(get(), get())
    }
}

val viewModelHomeModule = module {
    viewModel {
        HomeViewModel(get())
    }
}