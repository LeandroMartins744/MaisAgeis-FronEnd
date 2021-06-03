package com.maisageis.ocorrencias.data.bo

import com.maisageis.ocorrencias.model.ErrorResponse
import com.maisageis.ocorrencias.model.request.BORelRequest
import com.maisageis.ocorrencias.model.request.StreetCepRequest
import com.maisageis.ocorrencias.model.request.StreetRequest
import com.maisageis.ocorrencias.model.response.*
import java.lang.Exception

interface BOApi{
    suspend fun geCategory(onSuccess: (List<CategoryResponse>) -> Unit, onError: (ErrorResponse) -> Unit)
    suspend fun geDetails(value: BORelRequest, onSuccess: (CoodResponse) -> Unit, onError: (ErrorResponse) -> Unit)
}