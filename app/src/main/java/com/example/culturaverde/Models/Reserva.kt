package com.example.culturaverde.Models

import com.google.gson.annotations.SerializedName
import java.util.*

class Reserva {

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("productor_id")
    var productor: Productor? = null

    @SerializedName("consumidor_id")
    var consumidor: Consumidor? = null

    @SerializedName("punto_entrega_id")
    var puntoEntrega: PuntosEntrega? = null

    @SerializedName("estado_id")
    var estado_reserva: EstadoReserva? = null

    @SerializedName("fecha")
    var fecha: Date? = null

    @SerializedName("fecha_creacion")
    var fecha_creacion: Date?=null

    @SerializedName("total_reserva")
    var total_reserva: Float? = null

    @SerializedName("forma_retiro")
    var forma_retiro: String? = null

    @SerializedName("persona_retiro")
    var persona_retiro: String? = null

    @SerializedName("unidad_venta")
    var detallesReserva: List<DetalleReserva>? = arrayListOf()

    @SerializedName("contenido")
    var contenido: String? = null

    @SerializedName("fecha_vencimiento")
    var fecha_vencimiento: Date? = null


}