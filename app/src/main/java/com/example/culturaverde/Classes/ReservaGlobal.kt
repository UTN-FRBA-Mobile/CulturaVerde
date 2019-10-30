package com.example.culturaverde.Classes

import com.example.culturaverde.Models.Reserva
import io.paperdb.Paper

class ReservaGlobal {

    companion object {

        fun guardarReserva(reserva: Reserva) {
            Paper.book().write("reserva", reserva)
        }

        fun getReserva(): Reserva {
            return Paper.book().read("reserva")
        }
    }

}