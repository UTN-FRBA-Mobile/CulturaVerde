package com.example.culturaverde.Classes

import android.content.Context
import com.example.culturaverde.Models.Usuario
import io.paperdb.Paper

class UsuarioGlobal {

    companion object {

        fun guardarUsuario(usuario:Usuario) {
            Paper.book().write("usuario", usuario)
        }

        fun getUsuario(): Usuario? {
            return Paper.book().read("usuario")
        }

    }

    }