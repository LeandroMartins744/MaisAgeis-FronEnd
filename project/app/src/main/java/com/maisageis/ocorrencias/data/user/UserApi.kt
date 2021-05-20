package com.maisageis.ocorrencias.data.user

import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.UserRequest
import com.maisageis.ocorrencias.model.response.StreetResponse
import com.maisageis.ocorrencias.model.response.UserResponse

interface UserApi {
    suspend fun postStreet(value: UserRequest, onSuccess: (UserResponse) -> Unit, onError: (ErrorResponse) -> Unit)
}