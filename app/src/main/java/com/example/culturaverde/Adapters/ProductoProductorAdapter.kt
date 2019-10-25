package com.example.culturaverde.Adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Models.ProductoProductor
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.productos_productor_item.view.*

class ProductoProductorAdapter(var context: Context, var products: List<ProductoProductor> = arrayListOf()) :
    RecyclerView.Adapter<ProductoProductorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductoProductorAdapter.ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.productos_productor_item, null)
        return ViewHolder(view)

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ProductoProductorAdapter.ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindProduct(products[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // This displays the product information for each item
        fun bindProduct(product: ProductoProductor) {

            itemView.product_name2.text = product.titulo
            itemView.product_stock2.text =
                "Cantidad: " + product.stock.toString() + " " + "/" + " " + product.unidad_venta
            itemView.product_price2.text = "$${product.precio.toString()}"

            val imageBytes = Base64.decode(product.imagenes[0].image, 0)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            itemView.product_image3.setImageBitmap(image)

        }

    }

}