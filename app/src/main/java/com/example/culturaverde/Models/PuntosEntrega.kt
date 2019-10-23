package com.example.culturaverde.Models

import com.google.gson.annotations.SerializedName

class PuntosEntrega {

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("cod_postal")
    var codpostal: Int? = null

    @SerializedName("direccion")
    var direccion: Char? = null

    @SerializedName("latitud")
    var latitud: Float? = null

    @SerializedName("localidad")
    var localidad: Char? = null

    @SerializedName("longitud")
    var longitud: Float? = null

    @SerializedName("pais")
    var pais: Char? = null

    @SerializedName("provincia")
    var provincia: Char? = null

    @SerializedName("productor_id")
    var productorid: Long? = null

    @SerializedName("activo")
    var activo: Long? = null

}