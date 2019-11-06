package com.example.culturaverde.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Activities.ElegirPuntoEntregaRetiroActivity
import com.example.culturaverde.Activities.ResumenReservasActivity
import com.example.culturaverde.Classes.PuntoEntregaGlobal
import com.example.culturaverde.Models.CartItem
import com.example.culturaverde.Models.PuntosEntrega
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.busquedapuntosentrega_items.view.*

class PuntosEntregaAdapter (var context: Context, var puntosEntrega: List<PuntosEntrega> = arrayListOf()):
    RecyclerView.Adapter<PuntosEntregaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PuntosEntregaAdapter.ViewHolder {

        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.busquedapuntosentrega_items, null)
        return ViewHolder(view)

    }

    // This returns the size of the list.
    override fun getItemCount(): Int = puntosEntrega.size

    override fun onBindViewHolder(viewHolder: PuntosEntregaAdapter.ViewHolder, position: Int) {

        //we simply call the `bindItem` function here
        viewHolder.bindItem(puntosEntrega[position])

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(puntosEntrega: PuntosEntrega) {

            itemView.id_puntoentrega.text= puntosEntrega.id.toString()
            itemView.descrippuntoentrega.text = puntosEntrega.direccion.toString()

            itemView.puntosentrega_resultados.setOnClickListener { view ->

                PuntoEntregaGlobal.guardarPuntoEntrega(PuntosEntrega(puntosEntrega.id, puntosEntrega.reservas, puntosEntrega.fechas_entrega,
                                                                     puntosEntrega.cod_postal, puntosEntrega.direccion, puntosEntrega.latitud, puntosEntrega.localidad,
                                                                     puntosEntrega.longitud, puntosEntrega.pais, puntosEntrega.provincia, puntosEntrega.productor,
                                                                     puntosEntrega.activo))



                view.findNavController().navigate(R.id.action_busquedaPuntosEntregaFragment_to_elegirPuntoEntregaRetiroActivity)

            }
        }



    }


}

/*

class ShoppingCartAdapter(var context: Context, var cartItems: List<CartItem>) :
    RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

 */