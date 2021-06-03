package com.maisageis.ocorrencias.model.response

import com.google.gson.annotations.SerializedName

data class CoodResponse(
    @SerializedName("coord")
    val coord: CoordResponse,

    @SerializedName("details")
    val details: DetailsResponse,

    @SerializedName("city")
    val city: List<CityResponse>,

    @SerializedName("street")
    var street: List<CityResponse>,

    @SerializedName("category")
    val category: CategoryResponse
)

data class CityResponse (

    @SerializedName("date")
    val date: String,

    @SerializedName("total")
    val total: Int
)

data class CoordResponse (
    @SerializedName("street")
    val street: String,
    @SerializedName("streetLong")
    val streetLong: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("bairro")
    val bairro: String,
    @SerializedName("bairroLong")
    val bairroLong: String,
    @SerializedName("cidade")
    val cidade: String,
    @SerializedName("cidadeLong")
    val cidadeLong: String,
    @SerializedName("estado")
    val estado: String,
    @SerializedName("estadoLong")
    val estadoLong: String,
    @SerializedName("pais")
    val pais: String,
    @SerializedName("cep")
    val cep: String
)

data class DetailsResponse (
    @SerializedName("city")
    val city: Int,
    @SerializedName("districty")
    val districty: Int,
    @SerializedName("street")
    val street: Int
)
