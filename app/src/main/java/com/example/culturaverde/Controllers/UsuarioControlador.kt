package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.Usuario
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*
import retrofit2.Call
import java.util.*


interface UsuarioControlador {

        @Headers("Content-Type: application/json", "Accept: application/json")
        @GET("login")
        fun login(@Query("u") username: String, @Query("c") contraseña: String): Call<Usuario>

        @POST("usuario_productor")
        @FormUrlEncoded
        fun registrar(
                @Field("nombre") nombre: String,
                @Field("apellido") apellido: String,
                @Field("usuario") usuario: String,
                @Field("contraseña") contraseña: String,
                @Field("fecha_nacimiento") fecha_nacimiento: Date,
                @Field("rol") rol: String,
                @Field("telefono") telefono: String,
                @Query("razon_social") razon_social: String

        ): Call<Usuario>

        @PUT("users/{username}")
        fun update(@Path("username") username: String, @Body user: Usuario): Observable<Usuario>


        /*@DELETE("users/{username}")
        fun delete(@Path("username") username: String): Observable<Response<Void>>
        */
    }
