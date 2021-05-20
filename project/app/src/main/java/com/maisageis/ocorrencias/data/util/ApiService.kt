package com.maisageis.ocorrencias.data.util

import com.maisageis.ocorrencias.model.request.StreetCepRequest
import com.maisageis.ocorrencias.model.request.StreetRequest
import com.maisageis.ocorrencias.model.request.UserRequest
import com.maisageis.ocorrencias.model.response.DataResult
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.model.response.StreetResponse
import com.maisageis.ocorrencias.model.response.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

    interface ApiService {

    @POST("project/api/Login/loginUser")
    @FormUrlEncoded
    fun getLogin(@Field("user") user: String, @Field("pass") pass: String) : Call<UserResponse>

    @POST("project/api/Login/loginUser")
    @FormUrlEncoded
    fun getLoginNew(@Field("user") user: String, @Field("pass") pass: String) : Response<UserResponse>

    @POST("project/api/Login/loginUser")
    @FormUrlEncoded
    suspend fun getLoginNew2(@Field("user") user: String, @Field("pass") pass: String) : Response<UserResponse>

    @GET("project/api/GetCode")
    fun geoLocation(@Path("latitude") latitude: String, @Path("longetitude") longetitude: String) : Call<DataResult>


    //Nova Versao
    //Street
    @GET("project/api/Street/{id}")
    fun getStreet(@Path("id") id: Int): Call<StreetResponse>

    @GET("project/api/Street}")
    fun postStreet(@Body value: StreetRequest): Call<Int>

    @GET("project/api/Street")
    fun putStreet(@Body value: StreetRequest): Call<Boolean>

    @POST("project/api/Street/GetCEP")
    fun getStreetGet(@Body value: StreetCepRequest): Call<StreetCepResponse>

    //User
    @POST("project/api/User")
    fun postStreetGet(@Body value: UserRequest): Call<UserResponse>

}