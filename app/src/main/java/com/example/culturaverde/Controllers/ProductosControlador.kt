package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.ProductoProductor
import retrofit2.Call
import retrofit2.http.*

interface ProductosControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("obtenerProductos/?busqueda=Manzana")
    fun getProducts(
    ): Call<List<ProductoProductor>>

}