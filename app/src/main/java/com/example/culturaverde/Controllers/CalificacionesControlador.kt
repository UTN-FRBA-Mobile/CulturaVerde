package com.example.culturaverde.Controllers

import retrofit2.Call
import retrofit2.http.*

interface CalificacionesControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("guardarCalificacion")
    fun guardarCalificacion(@Query("reserva_id") reserva_id: Long,@Body body:String): Call<Void>

}