package com.example.culturaverde.Models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ProductoProductor (

    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("productor")
    var productor: Productor? = null,

    @SerializedName("producto")
    var producto: Producto? = null,

    @SerializedName("oferta")
    var oferta: Oferta? = null,

    @SerializedName("titulo")
    var titulo: String? = null,

    @SerializedName("descripcion")
    var descripcion: String? = null,

    @SerializedName("precio")
    var precio: Int? = null,

    @SerializedName("imagenes")
    var imagenes: List<Photo> = arrayListOf(),

    @SerializedName("tiempo_preparacion")
    var tiempo_preparacion: Int? = null,

    @SerializedName("tipo_produccion")
    var tipo_produccion: String? = null,

    @SerializedName("stock")
    var stock: Int? = null,

    @SerializedName("unidad_venta")
    var unidad_venta: String? = null,

    @SerializedName("contenido")
    var contenido: String? = null,

    @SerializedName("fecha_vencimiento")
    var fecha_vencimiento: Date? = null,

    @SerializedName("activo")
    var activo: Boolean? = null
)