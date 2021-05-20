package com.maisageis.ocorrencias.model.response

import com.google.gson.annotations.SerializedName

data class LogResponse (
    @SerializedName("idLog")
    val idLog: Int,
    @SerializedName("dateInsert")
    val dateInsert: String,
    @SerializedName("dateUpdate")
    val dateUpdate: String,
    @SerializedName("dateDelete")
    val dateDelete: String,
    @SerializedName("isActive")
    val isActive: Boolean
)