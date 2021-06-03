package com.maisageis.ocorrencias.data.util

import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelperImpl(): ApiHelper {
    override suspend fun getUsers(
        login: String,
        pass: String,
        onSuccess: (UserResponse) -> Unit,
        onError: (ErrorResponse) -> Unit
    ) {
        val request = RetrofitInitializer.buildService(ApiService::class.java)
        request.getLogin(login, pass).enqueue(object : Callback<UserResponse> {
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
}