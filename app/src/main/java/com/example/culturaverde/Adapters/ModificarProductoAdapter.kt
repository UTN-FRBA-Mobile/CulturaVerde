package com.example.culturaverde.Adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Models.Photo
import com.example.culturaverde.Models.ProductoProductor
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import kotlinx.android.synthetic.main.producto_modificar_fragment.view.*
import kotlinx.android.synthetic.main.producto_modificar_row_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ModificarProductoAdapter(var context: Context, var imagenes: List<Photo> = arrayListOf()) :
    RecyclerView.Adapter<ModificarProductoAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ModificarProductoAdapter.ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.producto_modificar_row_item, null)
        return ViewHolder(view)

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = imagenes.size

    override fun onBindViewHolder(viewHolder: ModificarProductoAdapter.ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindProduct(imagenes[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        // This displays the product information for each item
        fun bindProduct(imagen: Photo) {

            if (imagen.uri != null) {

                itemView.product_image.setImageURI(imagen.uri)

            } else {
                val imageBytes = Base64.decode(imagen.image, 0)
                val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                itemView.product_image.setImageBitmap(image)
            }

        }
    }

}