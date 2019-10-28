package com.example.culturaverde.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Reserva (

    @Expose
    @SerializedName("id_reserva")
    val id_reserva: Long,

    @Expose
    @SerializedName("productor")
    val productor: Productor,

    @Expose
    @SerializedName("consumidor")
    val consumidor: Consumidor,

    @Expose
    @SerializedName("punto_entrega")
    val punto_entrega: PuntosEntrega,

    @Expose
    @SerializedName("estado_reserva")
    val estado_reserva: EstadoReserva,

    @Expose
    @SerializedName("fecha")
    val fecha: Date,

    @Expose
    @SerializedName("fecha_creacion")
    val fecha_creacion: Date,

    @Expose
    @SerializedName("total_reserva")
    val total_reserva:Float,

    @Expose
    @SerializedName("forma_retiro")
    val forma_retiro: String,

    @Expose
    @SerializedName("persona_retiro")
    val persona_retiro: String,

    @Expose
    @SerializedName("detalleReserva")
    val detalleReserva: List<DetalleReserva>,

    @Expose
    @SerializedName("calificacion")
    val calificacion: Calificacion,

    @Expose
    @SerializedName("contenido")
    val contenido: String,

    @Expose
    @SerializedName("fecha_vencimiento")
    val fecha_vencimiento: Date


)