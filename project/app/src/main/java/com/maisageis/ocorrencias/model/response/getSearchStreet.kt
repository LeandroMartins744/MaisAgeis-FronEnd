package com.maisageis.ocorrencias.model.response

import com.google.gson.annotations.SerializedName

data class getSearchStreet (
    @SerializedName("total")
    val total: Int,

    @SerializedName("tipo")
    val tipo: Int,

    @SerializedName("categoria")
    val categoria: String,

    @SerializedName("imagem")
    val imagem: String,

    @SerializedName("descricao")
    val descricao: String
)
