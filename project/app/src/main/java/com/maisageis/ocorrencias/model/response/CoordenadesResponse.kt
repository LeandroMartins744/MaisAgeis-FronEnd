package com.maisageis.ocorrencias.model.response

import com.google.gson.annotations.SerializedName

data class DataResult (

        @SerializedName("plus_code") val plus_code : Plus_code,
        @SerializedName("results") val results : List<Results>,
        @SerializedName("status") val status : String
)

data class Viewport (

        @SerializedName("northeast") val northeast : Northeast,
        @SerializedName("southwest") val southwest : Southwest
)

data class Location (

        @SerializedName("lat") val lat : Double,
        @SerializedName("lng") val lng : Double
)

data class Address_components (

        @SerializedName("long_name") val long_name : Int,
        @SerializedName("short_name") val short_name : Int,
        @SerializedName("types") val types : List<String>
)

data class Northeast (

        @SerializedName("lat") val lat : Double,
        @SerializedName("lng") val lng : Double
)

data class Plus_code (

        @SerializedName("compound_code") val compound_code : String,
        @SerializedName("global_code") val global_code : String
)

data class Geometry (

        @SerializedName("location") val location : Location,
        @SerializedName("location_type") val location_type : String,
        @SerializedName("viewport") val viewport : Viewport,
        @SerializedName("bounds") val bounds : String
)

data class Southwest (

        @SerializedName("lat") val lat : Double,
        @SerializedName("lng") val lng : Double
)

data class Results (

        @SerializedName("address_components") val address_components : List<Address_components>,
        @SerializedName("formatted_address") val formatted_address : String,
        @SerializedName("geometry") val geometry : Geometry,
        @SerializedName("place_id") val place_id : String,
        @SerializedName("plus_code") val plus_code : Plus_code,
        @SerializedName("types") val types : List<String>
)