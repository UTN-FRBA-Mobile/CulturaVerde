package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.Producto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CategoriasControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("redAgro/obtener_productos")
    fun getCategorias(): Call<List<Producto>>


}