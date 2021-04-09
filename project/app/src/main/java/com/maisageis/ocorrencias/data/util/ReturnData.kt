package com.maisageis.ocorrencias.data.util

import com.maisageis.ocorrencias.model.ErrorResponse

interface ReturnData {
    fun <T> Success(data: T)
    fun Error(error: ErrorResponse)
    fun Loading()
}