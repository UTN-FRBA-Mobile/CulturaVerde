package com.example.culturaverde.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EstadoReserva (

    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("nombre")
    val nombre: String

)