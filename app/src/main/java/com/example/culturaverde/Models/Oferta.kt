package com.example.culturaverde.Models

import com.google.gson.annotations.SerializedName

data class Oferta (


    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("porcentaje")
    var porcentaje:Int? = null,

    @SerializedName("activo")
    var activo: Boolean? = null


    )