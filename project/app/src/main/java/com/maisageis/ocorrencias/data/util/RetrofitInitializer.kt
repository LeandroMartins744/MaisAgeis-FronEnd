package com.maisageis.ocorrencias.data.util

import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInitializer {
    private val client = OkHttpClient.Builder().build().interceptors(BasicAuthInterceptor("maisageis", "1234"))
    private lateinit var credentials: String

    fun BasicAuthInterceptor(user: String?, password: String?) {
        this.credentials = Credentials.basic(user, password)
    }

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://www.maisageis.kinghost.net/project/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}