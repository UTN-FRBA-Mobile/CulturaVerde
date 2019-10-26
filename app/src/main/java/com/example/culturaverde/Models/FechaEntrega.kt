package com.example.culturaverde.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FechaEntrega (

    @Expose
    @SerializedName("id")
    var id: Long,

    @Expose
    @SerializedName("punto_entrega")
    var punto_entrega: PuntosEntrega,

    @Expose
    @SerializedName("fecha")
    var fecha: String,

    @Expose
    @SerializedName("hora_inicio")
    var hora_inicio: String,

    @Expose
    @SerializedName("hora_fin")
    var hora_fin: String


)