package com.example.culturaverde.Controllers

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.Call
import com.example.culturaverde.Models.PuntosEntrega


interface PuntosentregaControlador {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("puntos_entrega_productor")

    fun getPuntosentrega(
    ): Call<List<PuntosEntrega>>

}