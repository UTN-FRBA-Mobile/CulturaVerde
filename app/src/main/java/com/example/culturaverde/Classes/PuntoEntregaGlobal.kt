package com.example.culturaverde.Classes

import com.example.culturaverde.Models.PuntosEntrega
import io.paperdb.Paper

class PuntoEntregaGlobal {

    companion object{
        fun guardarPuntoEntrega(puntoEntrga: PuntosEntrega) {
            Paper.book().write("puntoEntrga", puntoEntrga)
        }

        fun getpuntoentrega(): PuntosEntrega{
            return Paper.book().read("puntoEntrga")
        }
    }

}