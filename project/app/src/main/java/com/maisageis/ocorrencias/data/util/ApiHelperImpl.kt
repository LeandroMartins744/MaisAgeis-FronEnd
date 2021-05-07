package com.maisageis.ocorrencias.data.util

import com.maisageis.ocorrencias.data.util.RetrofitInitializer.apiService
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.UserResponse
import com.maisageis.ocorrencias.repository.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
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

    override suspend fun  getUsers2(
        login: String,
        pass: String
    ) {
        val request = RetrofitInitializer.buildService(ApiService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            var response = request.getLoginNew(login, pass)
            withContext(Dispatchers.Main) {
                try {
                   return@withContext Result.Success(response.body()!!)
                } catch (e: HttpException) {
                    return@withContext Result.Error(ErrorResponse(response.code(), response.message()))
                }
            }
        }
    }


}