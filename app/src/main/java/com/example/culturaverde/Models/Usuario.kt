package com.example.culturaverde.Models

import java.util.*
import com.google.gson.annotations.SerializedName

    class Usuario {

        @SerializedName("id")
        var id: Long? = null

        @SerializedName("nombre")
        var nombre: String? =null

        @SerializedName("apellido")
        var apellido: String? =null

        @SerializedName("usuario")
        var usuario: String? =null

        @SerializedName("contraseña")
        var contraseña: String? =null

        @SerializedName("fecha_nacimiento")
        var fecha_nacimiento: Date?=null

        @SerializedName("rol")
        var rol: String? =null

        @SerializedName("telefono")
        val telefono: String? =null
    }