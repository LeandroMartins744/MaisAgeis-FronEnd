package com.maisageis.ocorrencias.data.utilutil

import com.maisageis.ocorrencias.data.util.Endpoint
import com.maisageis.ocorrencias.data.util.RetrofitInitializer
import com.maisageis.ocorrencias.data.util.ReturnData
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginData(){
    fun Login(returnData: ReturnData){
        val request = RetrofitInitializer.buildService(Endpoint::class.java)
        val call = request.getLogin()

        call.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful) {
                    val res: UserModel = response.body()!!
                    returnData.Success(res)
                }
                else {
                    returnData.Error(ErrorResponse(response.code(), response.message()))
                }
            }
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                returnData.Error(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }
}
