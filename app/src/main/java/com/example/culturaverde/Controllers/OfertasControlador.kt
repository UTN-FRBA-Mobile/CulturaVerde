package com.example.culturaverde.Controllers

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Query

interface OfertasControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @PUT("redAgro/guardarOferta")
    fun guardarOferta(@Query("id_producto_productor") id_producto_productor:Long, @Query("porcentaje") porcentaje:Int,
                      @Query("activo") activo:Boolean, @Query("id_oferta") id_oferta: Long): Call<Void>



}