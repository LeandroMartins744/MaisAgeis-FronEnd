package com.maisageis.ocorrencias.data.util

import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInitializer  {

    private val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor("maisageis", "123@"))
            .build()

    private val gson = GsonBuilder()
            .setLenient()
            .create();

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://www.maisageis.kinghost.net/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    fun getRetrofit(): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    /*private fun providerRetrofit() = Retrofit.Builder()
            .baseUrl("http://www.maisageis.kinghost.net/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()*/

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}