package com.maisageis.ocorrencias.data.util

import com.maisageis.ocorrencias.model.response.UserResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(login: String, pass: String): Response<UserResponse>
}