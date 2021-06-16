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

    suspend fun updateUser(value: UserRequest, onSuccess: (Boolean) -> Unit, onError: (ErrorResponse) -> Unit){
        return try {
            userApi.putUser(value, onSuccess, onError)
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

    suspend fun loginPassword(value: UserRequest, onSuccess: (Boolean) -> Unit, onError: (ErrorResponse) -> Unit){
        return try {
            userApi.getPassword(value, onSuccess, onError)
        } catch (ex: Exception){
            onError(ErrorResponse(message = ""))
        }
    }
}