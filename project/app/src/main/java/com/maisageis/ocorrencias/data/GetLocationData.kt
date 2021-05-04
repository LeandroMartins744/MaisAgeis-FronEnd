package com.maisageis.ocorrencias.data

import com.maisageis.ocorrencias.data.util.Endpoint
import com.maisageis.ocorrencias.data.util.RetrofitInitializer
import com.maisageis.ocorrencias.data.util.ReturnData
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.DataResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetLocationData(){
    fun Location(latitude: String, longetitude: String, returnData: ReturnData){
        val request = RetrofitInitializer.buildService(Endpoint::class.java)
        val call = request.geoLocation(latitude, longetitude)

        call.enqueue(object : Callback<DataResult> {
            override fun onResponse(call: Call<DataResult>, response: Response<DataResult>) {
                if (response.isSuccessful) {
                    val res: DataResult = response.body()!!
                    returnData.Success(res)
                }
                else {
                    returnData.Error(ErrorResponse(response.code(), response.message()))
                }
            }
            override fun onFailure(call: Call<DataResult>, t: Throwable) {
                returnData.Error(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }
}
