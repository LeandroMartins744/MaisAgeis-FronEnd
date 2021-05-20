package com.maisageis.ocorrencias.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("street")
    val street: StreetResponse,
    @SerializedName("login")
    val login: LoginResponse,
    @SerializedName("cpf")
    val cpf: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("log")
    val log: LogResponse
)