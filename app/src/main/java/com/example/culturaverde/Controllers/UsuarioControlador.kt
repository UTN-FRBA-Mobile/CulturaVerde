package com.example.culturaverde.Controllers

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*
import com.example.culturaverde.Models.Usuario.User

interface UsuarioControlador {

        /*@POST("users")
        fun create(@Body user: User): Observable<User>
        */
        @POST("redAgro/usuario_consumidor/")
        fun registrar(@Body user: User): Observable<User>

        @PUT("users/{username}")
        fun update(@Path("username") username: String, @Body user: User): Observable<User>

        /*@DELETE("users/{username}")
        fun delete(@Path("username") username: String): Observable<Response<Void>>
        */
    }
