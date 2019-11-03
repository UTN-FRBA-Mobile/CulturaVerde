package com.example.culturaverde.Adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Models.CartItem
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.cart_list_item.view.product_name
import kotlinx.android.synthetic.main.cart_list_item.view.product_quantity
import kotlinx.android.synthetic.main.resumenfinal_list_item.view.*

class ResumenReservasAdapter(var context: Context, var cartItems: List<CartItem>) :
    RecyclerView.Adapter<ResumenReservasAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ResumenReservasAdapter.ViewHolder {

        // The layout design used for each list item
        val layout = LayoutInflater.from(context).inflate(R.layout.resumenfinal_list_item, parent, false)

        return ResumenReservasAdapter.ViewHolder(layout)
    }

    // This returns the size of the list.
    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(viewHolder: ResumenReservasAdapter.ViewHolder, position: Int) {

        //we simply call the `bindItem` function here
        viewHolder.bindItem(cartItems[position])

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun bindItem(cartItem: CartItem) {


            // This displays the cart item information for each item
            // Picasso.get().load(cartItem.product.imagenes[0].nombre).fit().into(itemView.product_image2)

            val imageBytes = Base64.decode(cartItem.product.imagenes[0].image, 0)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            itemView.product_image3.setImageBitmap(image)

            itemView.product_name.text = cartItem.product.titulo

            itemView.product_price.text = "$${cartItem.product.precio}"

            itemView.product_quantity.text = cartItem.quantity.toString()

            itemView.product_tipounidad.text = cartItem.product.unidad_venta

        }
    }

}