package com.maisageis.ocorrencias.data.util

import com.maisageis.ocorrencias.model.response.DataResult
import com.maisageis.ocorrencias.model.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {

    @POST("project/api/Login/loginUser")
    @FormUrlEncoded
    fun getLogin(@Field("user") user: String, @Field("pass") pass: String) : Call<UserResponse>

    @GET("project/api/GetCode")
    fun geoLocation(@Path("latitude") latitude: String, @Path("longetitude") longetitude: String) : Call<DataResult>
}