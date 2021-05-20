package com.maisageis.ocorrencias.model.request

import com.google.gson.annotations.SerializedName

    data class StreetCepRequest(
    @SerializedName("cep")
    var cep: String
)

data class StreetRequest(
    @SerializedName("idStreet")
    val idStreet: Long,
    @SerializedName("street")
    val street: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("complement")
    val complement: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longetude")
    val longetude: String
)