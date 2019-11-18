package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.Calificacion
import retrofit2.Call
import retrofit2.http.*

interface CalificacionesControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("redAgro/guardarCalificacion")
    fun guardarCalificacion(@Query("reserva_id") reserva_id: Long,@Body body:String): Call<Void>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("redAgro/obtenerCalificaciones")
    fun getCalificaciones(@Query("id_productor") id_productor:Long): Call<List<Calificacion>>


}