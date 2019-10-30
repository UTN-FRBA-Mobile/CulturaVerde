package com.example.culturaverde.Adapters

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Activities.ProductoSeleccionadoActivity
import com.example.culturaverde.Activities.ResultadoBusqueda
import com.example.culturaverde.Classes.ProductoGlobal
import com.example.culturaverde.Classes.ShoppingCart
import com.example.culturaverde.Models.CartItem
import com.example.culturaverde.Models.ProductoProductor
import com.example.culturaverde.R
import com.example.culturaverde.Ui.Principalconsumidores.PrincipalConsumidoresFragmentDirections
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_resultado_busqueda.*
import kotlinx.android.synthetic.main.product_row_item.view.*
import kotlinx.android.synthetic.main.product_row_item.view.product_name
import kotlinx.android.synthetic.main.product_row_item.view.product_price




class ProductAdapter(var context: Context, var products: List<ProductoProductor> = arrayListOf()) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductAdapter.ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.product_row_item, null)
        return ViewHolder(view)

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ProductAdapter.ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindProduct(products[position],context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // This displays the product information for each item
        fun bindProduct(product: ProductoProductor,context:Context) {

            itemView.product_name.text = product.titulo
            itemView.product_stock.text = "Stock: "+product.stock.toString()+" "+"/"+" "+product.unidad_venta
            itemView.product_price.text = "$${product.precio.toString()}"+" (x unidad)"

            val imageBytes = Base64.decode(product.imagenes[0].image, 0)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            itemView.product_image.setImageBitmap(image)

            itemView.product_image.setOnClickListener{view->

                ProductoGlobal.guardarProducto(product)

                val myIntent = Intent(
                    view.context,
                    ProductoSeleccionadoActivity::class.java)
                view.context.startActivity(myIntent)
            }


            Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {

                itemView.addToCart.setOnClickListener { view ->

                    val item = CartItem(product)

                        if(ShoppingCart.addItem(item)) {

                            //notify users
                            Snackbar.make(
                                (itemView.context as ResultadoBusqueda).coordinator,
                                "${product.titulo} se agregó a tu carrito",
                                Snackbar.LENGTH_LONG
                            ).show()

                            it.onNext(ShoppingCart.getCart())

                        }

                    else{

                            Snackbar.make(
                                (itemView.context as ResultadoBusqueda).coordinator,
                                "No hay mas stock de ese producto",
                                Snackbar.LENGTH_LONG
                            ).show()

                        }


                }
/*
                itemView.removeItem.setOnClickListener { view ->

                    val item = CartItem(product)

                    ShoppingCart.removeItem(item, itemView.context)

                    Snackbar.make(
                        (itemView.context as ResultadoBusqueda).coordinator,
                        "${product.titulo} se removió de tu carrito",
                        Snackbar.LENGTH_LONG
                    ).show()

                    it.onNext(ShoppingCart.getCart())
                }

*/
            }).subscribe { cart ->
                var quantity = 0

                cart.forEach { cartItem ->
                    quantity += cartItem.quantity
                }

                (itemView.context as ResultadoBusqueda).cart_size.text = quantity.toString()
                Toast.makeText(itemView.context, "Productos en carrito: $quantity", Toast.LENGTH_SHORT).show()
            }


        }

    }

}