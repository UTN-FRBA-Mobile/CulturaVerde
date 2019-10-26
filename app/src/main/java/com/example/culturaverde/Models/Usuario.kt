package com.example.culturaverde.Models

import com.google.gson.annotations.Expose
import java.util.*
import com.google.gson.annotations.SerializedName

    data class Usuario (

       /* @Expose
        @SerializedName("id")
        var id: Long? = null,
*/
        @Expose
        @SerializedName("nombre")
        var nombre: String? =null,

        @Expose
        @SerializedName("apellido")
        var apellido: String? =null,

        @Expose
        @SerializedName("usuario")
        var usuario: String? =null,

        @Expose
        @SerializedName("contraseña")
        var contraseña: String? =null,

        @Expose
        @SerializedName("fecha_nacimiento")
        var fecha_nacimiento: Date?=null,

        @Expose
        @SerializedName("rol")
        var rol: String? =null,

        @Expose
        @SerializedName("telefono")
        var telefono: String? =null
    )