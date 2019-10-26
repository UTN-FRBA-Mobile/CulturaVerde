package com.example.culturaverde.Adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Models.Reserva
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.reservas_item.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat


class ReservasAdapter(var context: Context, var reservas: List<Reserva> = arrayListOf()) :
    RecyclerView.Adapter<ReservasAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ReservasAdapter.ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.reservas_item, null)
        return ViewHolder(view)

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = reservas.size

    override fun onBindViewHolder(viewHolder: ReservasAdapter.ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindProduct(reservas[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // This displays the product information for each item

        fun bindProduct(reserva: Reserva) {

            itemView.texto_estado.text = "Estado"
            itemView.estado.text = reserva.estado_reserva!!.nombre

            itemView.datos_retiro.text = "Datos de retiro"
            itemView.texto_fecha_creacion.text = "Fecha de creación"

            val date = SimpleDateFormat("dd/MM/yyy").format(reserva.fecha_creacion)
            itemView.fecha_creacion.text = "${date}"

            itemView.persona_retiro.text = "Retira "+reserva.persona_retiro
            itemView.direccion_retiro.text= reserva.punto_entrega!!.direccion

            itemView.localidad_retiro.text= reserva.punto_entrega!!.localidad

            val date2 = SimpleDateFormat("dd/MM/yyy").format(reserva.fecha)
            itemView.fecha_retiro.text="${date2}"

            itemView.usuario.text = "Usuario"
            itemView.nombre.text = "Federico"
            itemView.apellido.text="Fernandez"
            itemView.tel.text="1530245798"

            itemView.total_reserva.text = "Total"
            itemView.total.text = "$${reserva.total_reserva}"


        }

    }


}