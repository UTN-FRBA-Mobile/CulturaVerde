package com.example.culturaverde.Models

import java.util.*

object Usuario {
    data class User(val id: Int, val nombre: String, val apellido: String, val usuario: String,
                    val contraseña: String, val fecha_nacimiento: Date, val rol: String, val telefono: String)

}