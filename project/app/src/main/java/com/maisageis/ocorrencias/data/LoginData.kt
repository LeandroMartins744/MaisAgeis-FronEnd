package com.maisageis.ocorrencias.data.utilutil

import com.maisageis.ocorrencias.data.util.Endpoint
import com.maisageis.ocorrencias.data.util.RetrofitInitializer
import com.maisageis.ocorrencias.data.util.ReturnData
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.UserModel
import com.maisageis.ocorrencias.model.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginData(){
    fun Login(login: String, pass: String, returnData: ReturnData){
        val request = RetrofitInitializer.buildService(Endpoint::class.java)
        val call = request.getLogin(login, pass)

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val res: UserResponse = response.body()!!
                    returnData.Success(res)
                }
                else {
                    returnData.Error(ErrorResponse(response.code(), response.message()))
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                returnData.Error(ErrorResponse(message = t.message!!, out = t.cause))
            }
        })
    }
}
