package com.example.culturaverde.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Classes.CategoriaProductoGlobal
import com.example.culturaverde.Models.Producto
import com.example.culturaverde.R
import com.example.culturaverde.Ui.Principalconsumidores.Categorias.CategoriasFragmentDirections
import com.example.culturaverde.Ui.Principalconsumidores.PrincipalConsumidoresFragmentDirections
import kotlinx.android.synthetic.main.categorias_item.view.*
import kotlinx.android.synthetic.main.estado_reserva_fragment.view.*

class CategoriasAdapter(var context: Context, var productos: List<Producto> = arrayListOf()) :
RecyclerView.Adapter<CategoriasAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoriasAdapter.ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.categorias_item, null)
        return ViewHolder(view)

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = productos.size

    override fun onBindViewHolder(viewHolder: CategoriasAdapter.ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindProduct(productos[position])


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // This displays the product information for each item

        fun bindProduct(producto: Producto) {

            itemView.nombre_categoria.text=producto.tipo


            itemView.card_categoria.setOnClickListener{view->

                CategoriaProductoGlobal.guardarProducto(Producto(producto.id,producto.categoria,producto.tipo))

                view.findNavController().navigate(R.id.action_nav_categoriasFragment_to_nav_resultadoBusqueda)

            }

        }


    }


    }