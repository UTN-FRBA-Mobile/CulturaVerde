package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.Reserva
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ReservasControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("get_reservas_usuario")
    fun getReservas(@Query("id") id:Long): Call<List<Reserva>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @PUT("actualizarEstadoReserva")
    fun modificarEstado(@Query("id_reserva") id_reserva:Long,

                        @Query("id_estado") id_estado:Long,
                       @Query("rol") rol:String): Call<String>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("generarReserva")
    fun generarReserva(@Body body:String): Call<Void>


}