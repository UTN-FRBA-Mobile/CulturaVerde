package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.Usuario

import retrofit2.http.*
import retrofit2.Call


interface UsuarioControlador {

        @Headers("Content-Type: application/json", "Accept: application/json")
        @GET("login")
        fun login(@Query("u") username: String, @Query("c") contraseña: String): Call<Usuario>

        @Headers("Content-Type: application/json", "Accept: application/json")
        @POST("usuario_productor")
        fun registrar(@Body body:String, @Query("razon_social") razon_social: String): Call<Usuario>

        @Headers("Content-Type: application/json", "Accept: application/json")
        @POST("usuario_consumidor")
        fun registrarConsumidor(@Body body:String): Call<Usuario>

        @Headers("Content-Type: application/json", "Accept: application/json")
        @PUT("update_usuario")
        fun editarDatosUsuario(@Body body:String, @Query("id") id: Int): Call<Void>

        @Headers("Content-Type: application/json", "Accept: application/json")
        @POST("registrar_token")
        fun registrarToken(@Body body:String): Call<Void>


        /*@DELETE("users/{username}")
        fun delete(@Path("username") username: String): Observable<Response<Void>>
        */
    }
