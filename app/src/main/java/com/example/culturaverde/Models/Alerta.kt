package com.example.culturaverde.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Alerta (

    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("nombre")
    val nombre: String

)