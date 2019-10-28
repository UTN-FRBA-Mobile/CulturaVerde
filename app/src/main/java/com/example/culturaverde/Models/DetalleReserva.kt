package com.example.culturaverde.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetalleReserva (

    @Expose
    @SerializedName("id_reserva")
    val id_reserva: Long,

    @Expose
    @SerializedName("id_producto")
    val id_producto: Long,

    @Expose
    @SerializedName("producto")
    val producto: ProductoProductor,

    @Expose
    @SerializedName("cantidad")
    val cantidad: Int,

    @Expose
    @SerializedName("activo")
    val activo: Boolean,

    @Expose
    @SerializedName("precio_por_unidad")
    val precio_por_unidad:Float





    )