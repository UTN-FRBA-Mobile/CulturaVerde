package com.example.culturaverde.Models

import com.google.gson.annotations.SerializedName

data class Productor (

    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("usuario")
    var usuario: Usuario,


    @SerializedName("razon_social")
    var razon_social: String? = null

)