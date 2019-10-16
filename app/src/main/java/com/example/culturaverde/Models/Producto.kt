package com.example.culturaverde.Models

import com.google.gson.annotations.SerializedName

class Producto {

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("categoria")
    var categoria: String? = null

    @SerializedName("tipo")
    var tipo: String? = null
}