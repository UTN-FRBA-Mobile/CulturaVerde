package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.ProductoProductor
import retrofit2.Call
import retrofit2.http.*

interface ProductosControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("obtenerProductosProductor")
    fun getProductos(@Query("id") id:Long): Call<List<ProductoProductor>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("obtenerProductos")
    fun getProductosBusqueda(@Query("busqueda") busqueda:String): Call<List<ProductoProductor>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @PUT("desactivar_producto")
    fun desactivarProductos(@Query("id") id: Long): Call<Void>

}