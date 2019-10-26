package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.Reserva
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ReservasControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("get_reservas_usuario/?id=5")
    fun getReservas(
    ): Call<List<Reserva>>


}