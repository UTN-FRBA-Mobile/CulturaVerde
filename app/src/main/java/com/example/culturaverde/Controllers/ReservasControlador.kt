package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.Reserva
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ReservasControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("get_reservas_usuario")
    fun getReservas(@Query("id") id:Long): Call<List<Reserva>>


}