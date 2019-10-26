package com.example.culturaverde.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PuntosEntrega (

    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("reservas")
    val reservas: List<Reserva>,

    @Expose
    @SerializedName("fechas_entrega")
    val fechas_entrega: List<FechaEntrega>,

    @Expose
    @SerializedName("cod_postal")
    val cod_postal: String,

    @Expose
    @SerializedName("direccion")
    val direccion: String,

    @Expose
    @SerializedName("latitud")
    val latitud: Double,

    @Expose
    @SerializedName("localidad")
    val localidad: String,

    @Expose
    @SerializedName("longitud")
    val longitud:Double,

    @Expose
    @SerializedName("pais")
    val pais: String,

    @Expose
    @SerializedName("provincia")
    val provincia: String,

    @Expose
    @SerializedName("productor")
    val productor: Productor,

    @Expose
    @SerializedName("activo")
    val activo: Boolean

    )