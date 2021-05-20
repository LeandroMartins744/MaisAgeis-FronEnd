package com.maisageis.ocorrencias.model.response

import com.google.gson.annotations.SerializedName

data class StreetResponse(
    @SerializedName("idStreet")
    val idStreet: Int,
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

data class StreetCepResponse(
    @SerializedName("cep")
    val cep: String,
    @SerializedName("logradouro")
    val street: String,
    @SerializedName("complemento")
    val complement: String,
    @SerializedName("bairro")
    val district: String,
    @SerializedName("localidade")
    val city: String,
    @SerializedName("uf")
    val state: String
)