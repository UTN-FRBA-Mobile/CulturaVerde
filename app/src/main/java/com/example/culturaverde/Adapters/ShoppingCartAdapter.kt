package com.example.culturaverde.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Models.CartItem
import com.example.culturaverde.R
import com.squareup.picasso.Picasso
import android.graphics.BitmapFactory
import android.util.Base64
import kotlinx.android.synthetic.main.cart_list_item.view.*

class ShoppingCartAdapter(var context: Context, var cartItems: List<CartItem>) :
    RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ShoppingCartAdapter.ViewHolder {

        // The layout design used for each list item
        val layout = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false)

        return ViewHolder(layout)
    }

    // This returns the size of the list.
    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(viewHolder: ShoppingCartAdapter.ViewHolder, position: Int) {

        //we simply call the `bindItem` function here
        viewHolder.bindItem(cartItems[position])
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(cartItem: CartItem) {

            // This displays the cart item information for each item
           // Picasso.get().load(cartItem.product.imagenes[0].nombre).fit().into(itemView.product_image2)

            val imageBytes = Base64.decode(cartItem.product.imagenes[0].image, 0)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            itemView.product_image2.setImageBitmap(image)

            itemView.product_name.text = cartItem.product.titulo

            itemView.product_price.text = "$${cartItem.product.precio}"

            itemView.product_quantity.text = cartItem.quantity.toString()

        }
    }

}