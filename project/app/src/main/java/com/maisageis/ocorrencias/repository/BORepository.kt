package com.maisageis.ocorrencias.repository

import com.maisageis.ocorrencias.data.bo.BOApi
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.response.CategoryResponse
import com.maisageis.ocorrencias.model.response.StreetResponse

class BORepository(private val boApi: BOApi) {
    suspend fun geCategory(onSuccess: (List<CategoryResponse>) -> Unit, onError: (ErrorResponse) -> Unit){
        return try {
            boApi.geCategory(onSuccess, onError)
        } catch (ex: Exception){
            onError(ErrorResponse(message = ""))
        }
    }
}