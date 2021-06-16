package com.maisageis.ocorrencias.model.request

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("IdStreet")
    val IdStreet: Int = 0,
    @SerializedName("cpf")
    val cpf: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("surname")
    val surname: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("password")
    val password: String = "",
    @SerializedName("street")
    val street: String = "",
    @SerializedName("number")
    val number: String = "",
    @SerializedName("complement")
    val complement: String = "",
    @SerializedName("district")
    val district: String = "",
    @SerializedName("city")
    val city: String = "",
    @SerializedName("state")
    val state: String = "",
    @SerializedName("latitude")
    val latitude: String = "",
    @SerializedName("longetude")
    val longetude: String = ""
)