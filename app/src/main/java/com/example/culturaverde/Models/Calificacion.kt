package com.example.culturaverde.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Date

data class Calificacion (

    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("id_reserva")
    val id_reserva: Long,

    @Expose
    @SerializedName("valor")
    val valor: Int,

    @Expose
    @SerializedName("comentario")
    val comentario: String,

    @Expose
    @SerializedName("fecha_calificacion")
    val fecha_calificacion: Date


)