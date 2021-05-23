package com.maisageis.ocorrencias.repository

import com.maisageis.ocorrencias.data.user.UserApi
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.LoginRequest
import com.maisageis.ocorrencias.model.request.UserRequest
import com.maisageis.ocorrencias.model.response.UserResponse

class UserRepository(private val userApi: UserApi) {
    suspend fun insertUser(value: UserRequest, onSuccess: (UserResponse) -> Unit, onError: (ErrorResponse) -> Unit){
        return try {
            userApi.postUser(value, onSuccess, onError)
        } catch (ex: Exception){
            onError(ErrorResponse(message = ""))
        }
    }

    suspend fun loginUser(value: LoginRequest, onSuccess: (UserResponse) -> Unit, onError: (ErrorResponse) -> Unit){
        return try {
            userApi.getLogin(value, onSuccess, onError)
        } catch (ex: Exception){
            onError(ErrorResponse(message = ""))
        }
    }
}