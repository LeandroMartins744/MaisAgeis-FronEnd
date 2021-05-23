package com.maisageis.ocorrencias.model.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("reference")
    val reference: String
)