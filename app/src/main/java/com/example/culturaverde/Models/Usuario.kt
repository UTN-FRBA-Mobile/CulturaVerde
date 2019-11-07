package com.example.culturaverde.Models

import com.google.gson.annotations.Expose
import java.util.*
import com.google.gson.annotations.SerializedName

    data class Usuario (
        @Expose
        @SerializedName("id")
        var id: Long,

        @Expose
        @SerializedName("nombre")
        var nombre: String,

        @Expose
        @SerializedName("apellido")
        var apellido: String,

        @Expose
        @SerializedName("usuario")
        var usuario: String,

        @Expose
        @SerializedName("contraseña")
        var contraseña: String,

        @Expose
        @SerializedName("fecha_nacimiento")
        var fecha_nacimiento: Date,

        @Expose
        @SerializedName("rol")
        var rol: String,

        @Expose
        @SerializedName("telefono")
        var telefono: String
    )