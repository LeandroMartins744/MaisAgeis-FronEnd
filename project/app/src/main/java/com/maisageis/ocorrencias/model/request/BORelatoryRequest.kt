package com.maisageis.ocorrencias.model.request

import com.google.gson.annotations.SerializedName

data class BORelRequest(
    @SerializedName("Category")
    val Category: String = "",

    @SerializedName("City")
    val City: String = "",

    @SerializedName("Street")
    val Street: String = "",

    @SerializedName("Latitude")
    val Latitude: String = "",

    @SerializedName("Longetude")
    val Longetude: String = ""
)