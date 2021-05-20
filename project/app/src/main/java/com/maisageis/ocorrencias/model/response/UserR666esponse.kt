package com.maisageis.ocorrencias.model.response

import com.google.gson.annotations.SerializedName

data class UserRes44ponse(
    @SerializedName("id")
    var id: Int,

    @SerializedName("usuario")
    var usuario: String,

    @SerializedName("dataCadastro")
    var dataCadastro: String,

    @SerializedName("dataAlteracao")
    var dataAlteracao: String,

    @SerializedName("dataDelete")
    var dataDelete: String,

    @SerializedName("ativo")
    var ativo: Boolean
)