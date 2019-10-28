package com.example.culturaverde.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Producto (

    @Expose
    @SerializedName("id")
    var id: Long,

    @Expose
    @SerializedName("categoria")
    var categoria: String,

    @Expose
    @SerializedName("tipo")
    var tipo: String
)