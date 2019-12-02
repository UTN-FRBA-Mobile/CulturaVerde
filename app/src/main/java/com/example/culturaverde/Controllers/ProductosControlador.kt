package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.ProductoProductor
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ProductosControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("redAgro/obtenerProductosProductor")
    fun getProductos(@Query("id") id:Long): Call<List<ProductoProductor>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("redAgro/obtenerProductos")
    fun getProductosBusqueda(@Query("busqueda") busqueda:String): Call<List<ProductoProductor>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @PUT("redAgro/actualizarEstadoProducto")
    fun desactivarProductos(@Query("id_producto_productor") id: Long,@Query("activo") activo: Boolean): Call<Void>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("redAgro/obtenerProductosProductorBusqueda")
    fun getProductosBusquedaProductor(@Query("id") id:Long): Call<List<ProductoProductor>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("redAgro/actualizarProductoProductor")
    fun editarDatosProducto(@Body body:String, @Query ("id_producto_productor") id_producto_productor:Long): Call<Void>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @PUT("redAgro/eliminarFoto")
    fun eliminarFoto(@Query ("id") id:Long): Call<Void>

    @Multipart
    @POST("redAgro/subir_archivos")
    fun subirFotos(@Part  multipartFile:MultipartBody.Part, @Query ("producto_productor") producto_productor:ProductoProductor): Call<Void>

}