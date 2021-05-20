package com.maisageis.ocorrencias.data.street

import com.maisageis.ocorrencias.data.util.ApiService
import com.maisageis.ocorrencias.data.util.RetrofitInitializer
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.StreetCepRequest
import com.maisageis.ocorrencias.model.request.StreetRequest
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.model.response.StreetResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StreetApiImp() : StreetApi {
    override suspend fun getStreet(
        id: Int,
        onSuccess: (StreetResponse) -> Unit,
        onError: (ErrorResponse) -> Unit
    ) {
        val request = RetrofitInitializer.buildService(ApiService::class.java)
        request.getStreet(id).enqueue(object : Callback<StreetResponse> {
            override fun onResponse(call: Call<StreetResponse>, response: Response<StreetResponse>) {
                if (response.isSuccessful)
                    onSuccess(response.body()!!)
                else
                    onError(ErrorResponse(response.code(), response.message()))
            }

            override fun onFailure(call: Call<StreetResponse>, t: Throwable) {
                onError(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }

    override suspend fun postStreet(
        value: StreetRequest,
        onSuccess: (Int) -> Unit,
        onError: (ErrorResponse) -> Unit
    ) {
        val request = RetrofitInitializer.buildService(ApiService::class.java)
        request.postStreet(value).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful)
                    onSuccess(response.body()!!)
                else
                    onError(ErrorResponse(response.code(), response.message()))
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                onError(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }

    override suspend fun putStreet(
        value: StreetRequest,
        onSuccess: (Boolean) -> Unit,
        onError: (ErrorResponse) -> Unit
    ) {
        val request = RetrofitInitializer.buildService(ApiService::class.java)
        request.putStreet(value).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful)
                    onSuccess(response.body()!!)
                else
                    onError(ErrorResponse(response.code(), response.message()))
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                onError(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }

    override suspend fun getStreetGet(
        value: StreetCepRequest,
        onSuccess: (StreetCepResponse) -> Unit,
        onError: (ErrorResponse) -> Unit
    ) {
        val request = RetrofitInitializer.buildService(ApiService::class.java)
        request.getStreetGet(value).enqueue(object : Callback<StreetCepResponse> {
            override fun onResponse(call: Call<StreetCepResponse>, response: Response<StreetCepResponse>) {
                if (response.isSuccessful)
                    onSuccess(response.body()!!)
                else
                    onError(ErrorResponse(response.code(), response.message()))
            }

            override fun onFailure(call: Call<StreetCepResponse>, t: Throwable) {
                onError(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }
}