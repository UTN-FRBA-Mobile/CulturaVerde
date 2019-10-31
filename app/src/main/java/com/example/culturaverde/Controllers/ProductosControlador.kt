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
    @PUT("actualizarEstadoProducto")
    fun desactivarProductos(@Query("id_producto_productor") id: Long,@Query("activo") activo: Boolean): Call<Void>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("obtenerProductosProductorBusqueda")
    fun getProductosBusquedaProductor(@Query("id") id:Long): Call<List<ProductoProductor>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @PUT("obtenerProductosProductorBusqueda")
    fun editarDatosProducto(@Body body:String): Call<Void>

}