package com.example.culturaverde.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Models.FechaEntrega
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.busquedafechasentrega_items.view.*

class FechaEntregaAdapter (var context: Context, var fechaEntrega: List<FechaEntrega> = arrayListOf()):
    RecyclerView.Adapter<FechaEntregaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FechaEntregaAdapter.ViewHolder {

        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.busquedafechasentrega_items, null)
        return ViewHolder(view)

    }

    // This returns the size of the list.
    override fun getItemCount(): Int = fechaEntrega.size

    override fun onBindViewHolder(viewHolder: FechaEntregaAdapter.ViewHolder, position: Int) {

        //we simply call the `bindItem` function here
        viewHolder.bindItem(fechaEntrega[position])

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(fechaEntrega: FechaEntrega) {

            itemView.id_fechaentrega.text = fechaEntrega.id.toString()
            itemView.fechaentrega.text = fechaEntrega.fecha

        }

    }

}