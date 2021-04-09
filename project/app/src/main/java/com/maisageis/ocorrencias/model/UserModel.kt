package com.maisageis.ocorrencias.model

data class UserModel(val login: String, val pass: String)

data class ErrorResponse(val code: Int = 100, val message: String, val out: Throwable? = null)