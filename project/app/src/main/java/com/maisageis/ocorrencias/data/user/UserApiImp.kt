package com.maisageis.ocorrencias.data.user

import com.maisageis.ocorrencias.data.util.ApiService
import com.maisageis.ocorrencias.data.util.RetrofitInitializer
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.LoginRequest
import com.maisageis.ocorrencias.model.request.UserRequest
import com.maisageis.ocorrencias.model.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class UserApiImp(): UserApi {
    override suspend fun postUser(
        value: UserRequest,
        onSuccess: (UserResponse) -> Unit,
        onError: (ErrorResponse) -> Unit
    ) {
        val request = RetrofitInitializer.buildService(ApiService::class.java)
        request.postUser(value).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful)
                    onSuccess(response.body()!!)
                else
                    onError(ErrorResponse(response.code(), response.message()))
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onError(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }

    override suspend fun putUser(
        value: UserRequest,
        onSuccess: (Boolean) -> Unit,
        onError: (ErrorResponse) -> Unit
    ) {
        val request = RetrofitInitializer.buildService(ApiService::class.java)
        request.puttUser(value).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful)
                    onSuccess(true)
                else
                    onError(ErrorResponse(response.code(), response.message()))
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                onError(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }

    override suspend fun getLogin(
        value: LoginRequest,
        onSuccess: (UserResponse) -> Unit,
        onError: (ErrorResponse) -> Unit
    ) {
        val request = RetrofitInitializer.buildService(ApiService::class.java)
        request.getLogin(value).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                try {
                    if (response.isSuccessful && response.code() == 200)
                        onSuccess(response.body()!!)
                    else
                        onError(ErrorResponse(response.code(), response.message()))
                }
                catch (ex:Exception){
                    onError(ErrorResponse(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onError(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }

    override suspend fun getPassword(
        value: UserRequest,
        onSuccess: (Boolean) -> Unit,
        onError: (ErrorResponse) -> Unit
    ) {
        val request = RetrofitInitializer.buildService(ApiService::class.java)
        request.getLoginPassword(value).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                try {
                    if (response.isSuccessful && response.code() == 200)
                        onSuccess(response.body()!!)
                    else
                        onError(ErrorResponse(response.code(), response.message()))
                }
                catch (ex:Exception){
                    onError(ErrorResponse(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                onError(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }
}