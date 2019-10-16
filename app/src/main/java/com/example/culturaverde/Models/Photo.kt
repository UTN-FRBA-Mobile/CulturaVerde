package com.example.culturaverde.Models

import com.google.gson.annotations.SerializedName
import java.nio.ByteBuffer
import java.sql.Blob

class Photo {

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("image")
    var image: String?=null

    @SerializedName("nombre")
    var nombre: String? = null

    @SerializedName("tipo_contenido")
    var tipo_contenido: String? = null




}