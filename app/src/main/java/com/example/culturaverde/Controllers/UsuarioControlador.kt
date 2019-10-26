package com.example.culturaverde.Controllers

import com.example.culturaverde.Models.Usuario
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*
import retrofit2.Call
import java.util.*


interface UsuarioControlador {

        @Headers("Content-Type: application/json", "Accept: application/json")
        @GET("login")
        fun login(@Query("u") username: String, @Query("c") contraseña: String): Call<Usuario>

        @Headers("Content-Type: application/json", "Accept: application/json")
        @POST("usuario_productor")
        fun registrar(@Body body:String, @Query("razon_social") razon_social: String): Call<Void>

        @PUT("users/{username}")
        fun update(@Path("username") username: String, @Body user: Usuario): Observable<Usuario>


        /*@DELETE("users/{username}")
        fun delete(@Path("username") username: String): Observable<Response<Void>>
        */
    }
