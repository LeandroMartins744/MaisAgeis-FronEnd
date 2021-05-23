package com.maisageis.ocorrencias.data.bo

import com.maisageis.ocorrencias.data.util.ApiService
import com.maisageis.ocorrencias.data.util.RetrofitInitializer
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BOApiImp() : BOApi {
    override suspend fun geCategory(
        onSuccess: (List<CategoryResponse>) -> Unit,
        onError: (ErrorResponse) -> Unit
    ) {
        val request = RetrofitInitializer.buildService(ApiService::class.java)
        request.geCategory().enqueue(object : Callback<List<CategoryResponse>> {
            override fun onResponse(call: Call<List<CategoryResponse>>, response: Response<List<CategoryResponse>>) {
                if (response.isSuccessful)
                    onSuccess(response.body()!!)
                else
                    onError(ErrorResponse(response.code(), response.message()))
            }

            override fun onFailure(call: Call<List<CategoryResponse>>, t: Throwable) {
                onError(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }

}