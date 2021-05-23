package com.maisageis.ocorrencias.repository

import com.maisageis.ocorrencias.data.street.StreetApi
import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.StreetCepRequest
import com.maisageis.ocorrencias.model.request.StreetRequest
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.model.response.StreetResponse

class StreetRepository(private val streetApi: StreetApi) {

    suspend fun getStreet(id: Int, onSuccess: (StreetResponse) -> Unit, onError: (ErrorResponse) -> Unit){
        return try {
            streetApi.getStreet(id, onSuccess, onError)
        } catch (ex: Exception){
            onError(ErrorResponse(message = ""))
        }
    }

    suspend fun insertStreet(value: StreetRequest, onSuccess: (Int) -> Unit, onError: (ErrorResponse) -> Unit){
        return try {
            streetApi.postStreet(value, onSuccess, onError)
        } catch (ex: Exception){
            onError(ErrorResponse(message = ""))
        }
    }

    suspend fun updateStreet(value: StreetRequest, onSuccess: (Boolean) -> Unit, onError: (ErrorResponse) -> Unit){
        return try {
            streetApi.putStreet(value, onSuccess, onError)
        } catch (ex: Exception){
            onError(ErrorResponse(message = ""))
        }
    }

    suspend fun cepSearchStreet(value: StreetCepRequest, onSuccess: (StreetCepResponse) -> Unit, onError: (ErrorResponse) -> Unit){
        return try {
            streetApi.getStreetGet(value, onSuccess, onError)
        } catch (ex: Exception){
            onError(ErrorResponse(message = ""))
        }
    }
}
