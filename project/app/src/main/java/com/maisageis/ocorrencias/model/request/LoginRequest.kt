package com.maisageis.ocorrencias.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("Email")
    val user: String,
    @SerializedName("Password")
    val password: String
)