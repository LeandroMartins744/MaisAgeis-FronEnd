package com.maisageis.ocorrencias.data.util

import com.maisageis.ocorrencias.model.response.UserResponse
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(login: String, pass: String): Response<UserResponse> {
        return apiService.getLogin(login, pass)
    }

}