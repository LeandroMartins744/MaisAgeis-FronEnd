package com.maisageis.ocorrencias.data.bo

import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.StreetCepRequest
import com.maisageis.ocorrencias.model.request.StreetRequest
import com.maisageis.ocorrencias.model.response.CategoryResponse
import com.maisageis.ocorrencias.model.response.StreetCepResponse
import com.maisageis.ocorrencias.model.response.StreetResponse
import com.maisageis.ocorrencias.model.response.UserResponse
import java.lang.Exception

interface BOApi{
    suspend fun geCategory(onSuccess: (List<CategoryResponse>) -> Unit, onError: (ErrorResponse) -> Unit)
}