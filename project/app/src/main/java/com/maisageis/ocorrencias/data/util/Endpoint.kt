package com.maisageis.ocorrencias.data.util

import com.maisageis.ocorrencias.model.UserModel
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {

    @GET("76fa97e3-79fa-42d7-8d2c-bba42eb2e3d5")
    fun getLogin() : Call<UserModel>
}