package com.example.culturaverde.Models

import com.google.gson.annotations.SerializedName

data class Consumidor (

    @SerializedName("id")
    var id: Long,

    @SerializedName("usuario")
    var usuario: Usuario
)