package com.example.culturaverde.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Models.Calificacion
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.calificacion_item.view.*

class CalificacionesAdapter(var context: Context, var calificaciones: List<Calificacion> = arrayListOf()) :
    RecyclerView.Adapter<CalificacionesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CalificacionesAdapter.ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.calificacion_item, null)
        return ViewHolder(view)

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = calificaciones.size

    override fun onBindViewHolder(viewHolder: CalificacionesAdapter.ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindProduct(calificaciones[position])


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // This displays the product information for each item

        fun bindProduct(calificacion: Calificacion) {


            itemView.texto_comentario.text= calificacion.comentario

            itemView.id_reserva.text="Reserva #"+calificacion.reserva.toString()

            itemView.calificacion_recibida.rating=calificacion.valor.toFloat()

            itemView.nombre.text = calificacion.nombreConsumidor

            itemView.apellido.text = calificacion.apellidoConsumidor

        }


    }


}