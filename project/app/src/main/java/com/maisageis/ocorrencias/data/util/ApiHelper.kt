package com.maisageis.ocorrencias.data.util

import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.UserResponse
import java.lang.Exception

interface ApiHelper {
    suspend fun getUsers(login: String, pass: String, onSuccess: (UserResponse) -> Unit, onError: (ErrorResponse) -> Unit)
    suspend fun getUsers2(login: String, pass: String)
}