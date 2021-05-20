package com.maisageis.ocorrencias.data.street

import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.StreetCepRequest
import com.maisageis.ocorrencias.model.request.StreetRequest
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.model.response.StreetResponse
import com.maisageis.ocorrencias.model.response.UserResponse
import java.lang.Exception

interface StreetApi{
    suspend fun getStreet(id: Int, onSuccess: (StreetResponse) -> Unit, onError: (ErrorResponse) -> Unit)
    suspend fun postStreet(value: StreetRequest, onSuccess: (Int) -> Unit, onError: (ErrorResponse) -> Unit)
    suspend fun putStreet(value: StreetRequest, onSuccess: (Boolean) -> Unit, onError: (ErrorResponse) -> Unit)
    suspend fun getStreetGet(value: StreetCepRequest, onSuccess: (StreetCepResponse) -> Unit, onError: (ErrorResponse) -> Unit)
}