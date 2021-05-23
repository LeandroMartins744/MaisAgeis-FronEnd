package com.maisageis.ocorrencias.data.user

import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.LoginRequest
import com.maisageis.ocorrencias.model.request.UserRequest
import com.maisageis.ocorrencias.model.response.StreetResponse
import com.maisageis.ocorrencias.model.response.UserResponse

interface UserApi {
    suspend fun postUser(value: UserRequest, onSuccess: (UserResponse) -> Unit, onError: (ErrorResponse) -> Unit)
    suspend fun getLogin(value: LoginRequest, onSuccess: (UserResponse) -> Unit, onError: (ErrorResponse) -> Unit)
}