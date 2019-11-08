package com.example.culturaverde.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Activities.ElegirPuntoEntregaRetiroActivity
import com.example.culturaverde.Models.FechaEntrega
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.busquedafechasentrega_items.view.*
import kotlinx.android.synthetic.main.busquedapuntosentrega_items.view.*

class FechaEntregaAdapter (var context: Context, var fechasEntrega: List<FechaEntrega> = arrayListOf(),
                           var idproductor: String?, var nombreretira: String?, var apellidoretira: String?,
                           var idpuntoentrega: String?, var direccioncompleta: String?,
                           var fechaentregaid: String?, var fechaentrega: String?,
                           var horariodesde: String?, var horariohasta: String?):
    RecyclerView.Adapter<FechaEntregaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FechaEntregaAdapter.ViewHolder {

        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.busquedafechasentrega_items, null)
        return ViewHolder(view)

    }

    // This returns the size of the list.
    override fun getItemCount(): Int = fechasEntrega.size

    override fun onBindViewHolder(viewHolder: FechaEntregaAdapter.ViewHolder, position: Int) {

        //we simply call the `bindItem` function here
        viewHolder.bindItem(fechasEntrega[position], idproductor, nombreretira,
            apellidoretira, idpuntoentrega, direccioncompleta)

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(fechaEntrega: FechaEntrega, idproductor: String?, nombreretira: String?,
                     apellidoretira: String?, idpuntoentrega: String?, direccioncompleta: String?) {

            var fechaentregaid: String? = fechaEntrega.id.toString()
            var fechaentrega: String? = fechaEntrega.fecha
            var horariodesde: String? = fechaEntrega.hora_inicio
            var horariohasta: String? = fechaEntrega.hora_fin

            itemView.id_fechaentrega.text = fechaentregaid
            itemView.fechaentrega.text = fechaentrega

            itemView.fechasentrega_resultados.setOnClickListener { view ->

                val intent = Intent(view.context.applicationContext, ElegirPuntoEntregaRetiroActivity::class.java)

                intent.putExtra("Idproductor", idproductor)
                intent.putExtra("Nombreretira", nombreretira)
                intent.putExtra("Apellidoretira", apellidoretira)

                intent.putExtra("Idpuntoentrega", idpuntoentrega)
                intent.putExtra("Direccioncompleta", direccioncompleta)

                intent.putExtra("Fechaentregaid", fechaentregaid)
                intent.putExtra("Fechaentrega", fechaentrega)

                intent.putExtra("Horariodesde", horariodesde)
                intent.putExtra("Horariohasta", horariohasta)

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                ContextCompat.startActivity(view.context.applicationContext, intent, null)

            }



        }

    }

}