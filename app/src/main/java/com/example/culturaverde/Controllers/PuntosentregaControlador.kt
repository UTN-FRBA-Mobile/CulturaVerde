package com.example.culturaverde.Controllers

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.Call
import com.example.culturaverde.Models.PuntosEntrega
import retrofit2.http.Query


interface PuntosentregaControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("redAgro/puntos_entrega_productor")
    fun getPuntosentrega(): Call<List<PuntosEntrega>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("redAgro/puntos_productor")
    fun obtenerpuntosdeentrega(@Query("id") id:Long): Call<List<PuntosEntrega>>



}