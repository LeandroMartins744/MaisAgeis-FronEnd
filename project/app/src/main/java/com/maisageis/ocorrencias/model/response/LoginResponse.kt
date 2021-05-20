package com.maisageis.ocorrencias.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("user")
    val user: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("log")
    val log: LogResponse
)