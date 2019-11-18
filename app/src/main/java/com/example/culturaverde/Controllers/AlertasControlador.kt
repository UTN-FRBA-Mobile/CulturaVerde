package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.AlertaUsuario
import retrofit2.Call
import retrofit2.http.*

interface AlertasControlador {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("redAgro/obtenerConfiguracionAlertas")
    fun obtenerConfiguracionAlertas(@Query("id_usuario") id_usuario: Long): Call<List<AlertaUsuario>>


    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("redAgro/guardarConfiguracionAlertas")
    fun guardarConfiguracionAlertas(@Query("id_usuario") id_usuario: Long,@Body body:String): Call<Void>


}