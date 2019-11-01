package com.example.culturaverde.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Date

data class AlertaUsuario (
    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("alerta")
    val alerta: Alerta

)