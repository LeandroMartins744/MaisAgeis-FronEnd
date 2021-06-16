package com.maisageis.ocorrencias.data.util

import com.maisageis.ocorrencias.model.request.*
import com.maisageis.ocorrencias.model.response.*
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
    fun postUser(@Body value: UserRequest): Call<UserResponse>

    @PUT("project/api/User")
    fun puttUser(@Body value: UserRequest): Call<Boolean>

    @POST("project/api/User/LoginUser")
    fun getLogin(@Body value: LoginRequest): Call<UserResponse>

    @POST("project/api/User/Password")
    fun getLoginPassword(@Body value: UserRequest): Call<Boolean>


    //BO Category
    @GET("project/api/BOCategory")
    fun geCategory(): Call<List<CategoryResponse>>

    @POST("project/api/BO/City")
    fun geCity(@Body value: BORelRequest): Call<List<getSearchStreet>>

    @POST("project/api/BO/Category")
    fun geDetails(@Body value: BORelRequest): Call<CoodResponse>

}