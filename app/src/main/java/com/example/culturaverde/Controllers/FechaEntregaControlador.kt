package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.FechaEntrega
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FechaEntregaControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("fechas_punto_entrega")
    fun obtenerFechasPuntoDeEntrega(@Query("id_punto_entrega") id:Long): Call<List<FechaEntrega>>

}