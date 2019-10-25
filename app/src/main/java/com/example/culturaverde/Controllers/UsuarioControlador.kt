package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.Usuario
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*
import retrofit2.Call


interface UsuarioControlador {

        @Headers("Content-Type: application/json", "Accept: application/json")
        @GET("login")
        fun login(@Query("u") username: String, @Query("c") contraseña: String): Call<Usuario>

        @POST("redAgro/usuario_consumidor/")
        fun registrar(@Body user: Usuario): Observable<Usuario>

        @PUT("users/{username}")
        fun update(@Path("username") username: String, @Body user: Usuario): Observable<Usuario>


        /*@DELETE("users/{username}")
        fun delete(@Path("username") username: String): Observable<Response<Void>>
        */
    }
