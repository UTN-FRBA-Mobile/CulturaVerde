package com.example.culturaverde.Models

import com.google.gson.annotations.SerializedName

class Productor {

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("usuario")
    var usuario: Usuario? = null

    @SerializedName("razon_social")
    var razon_social: String? = null

}