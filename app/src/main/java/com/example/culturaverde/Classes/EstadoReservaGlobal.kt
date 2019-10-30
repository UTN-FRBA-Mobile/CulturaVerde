package com.example.culturaverde.Classes

import com.example.culturaverde.Models.EstadoReserva
import io.paperdb.Paper

class EstadoReservaGlobal {

    companion object {

        fun guardarEstado(estado:EstadoReserva) {
            Paper.book().write("estado", estado)
        }

        fun getEstado(): EstadoReserva {
            return Paper.book().read("estado")
        }

    }
}