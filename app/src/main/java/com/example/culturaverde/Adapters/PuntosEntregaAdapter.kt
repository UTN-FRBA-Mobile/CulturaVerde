package com.example.culturaverde.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Activities.ElegirPuntoEntregaRetiroActivity
import com.example.culturaverde.Models.PuntosEntrega
import kotlinx.android.synthetic.main.busquedapuntosentrega_items.view.*


class PuntosEntregaAdapter (var context: Context, var puntosEntrega: List<PuntosEntrega> = arrayListOf(),
                            var idproductor: String?, var nombreretira: String?, var apellidoretira: String?,
                            var idpuntoentrega: String?, var direccioncompleta: String?,
                            var fechaentregaid: String?, var fechaentrega: String?,
                            var horariodesde: String?, var horariohasta: String?):

    RecyclerView.Adapter<PuntosEntregaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PuntosEntregaAdapter.ViewHolder {

        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(com.example.culturaverde.R.layout.busquedapuntosentrega_items, null)


        return ViewHolder(view)

    }

    // This returns the size of the list.
    override fun getItemCount(): Int = puntosEntrega.size

    override fun onBindViewHolder(viewHolder: PuntosEntregaAdapter.ViewHolder, position: Int) {

        //we simply call the `bindItem` function here
        viewHolder.bindItem(puntosEntrega[position], idproductor, nombreretira,
            apellidoretira, fechaentregaid, fechaentrega)

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(puntosEntrega: PuntosEntrega, idproductor: String?, nombreretira: String?,
                     apellidoretira: String?, fechaentregaid: String?, fechaentrega: String?) {


            var direccioncompleta: String? = puntosEntrega.direccion+", "+puntosEntrega.localidad+", "+puntosEntrega.provincia
            var idpuntoentrega: String = puntosEntrega.id.toString()


            itemView.id_puntoentrega.text= idpuntoentrega
            itemView.descrippuntoentrega.text = direccioncompleta


            itemView.puntosentrega_resultados.setOnClickListener { view ->

                val intent = Intent(view.context.applicationContext, ElegirPuntoEntregaRetiroActivity::class.java)
                intent.putExtra("Idproductor", idproductor)
                intent.putExtra("Nombreretira", nombreretira)
                intent.putExtra("Apellidoretira", apellidoretira)

                intent.putExtra("Idpuntoentrega", idpuntoentrega)
                intent.putExtra("Direccioncompleta", direccioncompleta)


                startActivity(view.context.applicationContext, intent, null)

            }
        }

    }


}
