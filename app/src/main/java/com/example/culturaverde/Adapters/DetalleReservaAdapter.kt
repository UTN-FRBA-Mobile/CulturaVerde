package com.example.culturaverde.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Controllers.ReservasControlador
import com.example.culturaverde.Models.DetalleReserva
import com.example.culturaverde.Models.Reserva
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import kotlinx.android.synthetic.main.detalle_reserva_item.view.*
import java.text.SimpleDateFormat

class DetalleReservaAdapter(var context: Context, var detalle_reserva: List<DetalleReserva> = arrayListOf()) :
    RecyclerView.Adapter<DetalleReservaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DetalleReservaAdapter.ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.detalle_reserva_item, null)
        return ViewHolder(view)

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = detalle_reserva.size

    override fun onBindViewHolder(viewHolder: DetalleReservaAdapter.ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindProduct(detalle_reserva[position])


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // This displays the product information for each item

        fun bindProduct(detalle_reserva: DetalleReserva) {

            var cantidad:Int = detalle_reserva.cantidad
            var precio_unidad:Float = detalle_reserva.precio_por_unidad

              itemView.input_producto.text = detalle_reserva.producto.producto!!.tipo
               itemView.input_cantidad.text = detalle_reserva.cantidad.toString()
              itemView.input_precio_unidad.text = detalle_reserva.precio_por_unidad.toString()
             itemView.input_subtotal.text = (cantidad*precio_unidad).toString()



        }





    }




}