package com.example.culturaverde.Adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.culturaverde.Models.ProductoProductor
import com.example.culturaverde.R
import com.example.culturaverde.Ui.PrincipalProductor.Productos.ProductosProductorFragmentDirections
import kotlinx.android.synthetic.main.productos_productor_item.view.*
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.Classes.ProductoGlobal
import com.example.culturaverde.Controllers.ProductosControlador
import com.example.culturaverde.Services.APIConfig
import kotlinx.android.synthetic.main.productos_productor_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductoProductorAdapter(var context: Context, var products: List<ProductoProductor> = arrayListOf()) :
    RecyclerView.Adapter<ProductoProductorAdapter.ViewHolder>() {

    private val ctx:Context=context

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductoProductorAdapter.ViewHolder {
        // The layout design used for each list item
        val view = LayoutInflater.from(context).inflate(R.layout.productos_productor_item, null)
        return ViewHolder(view)

    }
    // This returns the size of the list.
    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ProductoProductorAdapter.ViewHolder, position: Int) {
        //we simply call the `bindProduct` function here
        viewHolder.bindProduct(products[position], ctx)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var productoControlador: ProductosControlador

        // This displays the product information for each item
        fun bindProduct(product: ProductoProductor, ctx: Context) {
            productoControlador =
                APIConfig.getRetrofitClient(ctx).create(ProductosControlador::class.java)

            itemView.product_name2.text = product.titulo
            itemView.product_stock2.text =
                "Cantidad: " + product.stock.toString() + " " + "/" + " " + product.unidad_venta
            itemView.product_price2.text = "$${product.precio.toString()}"

            val imageBytes = Base64.decode(product.imagenes[0].image, 0)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            itemView.product_image3.setImageBitmap(image)

            if(product.activo==true){
                itemView.eliminar_productos.visibility=View.VISIBLE
                itemView.activar_producto.visibility=View.GONE
            }
            else{
                itemView.eliminar_productos.visibility=View.GONE
                itemView.activar_producto.visibility=View.VISIBLE
            }

            itemView.editar_producto.setOnClickListener{
                ProductoGlobal.guardarProducto(product)
                val action =
                    ProductosProductorFragmentDirections.actionNavProductosProductorFragmentToProductoModificarFragment()
                itemView.findNavController().navigate(action)
            }


            itemView.eliminar_productos.setOnClickListener{
                itemView.eliminar_productos.visibility=View.GONE
                itemView.activar_producto.visibility=View.VISIBLE
                productoControlador.desactivarProductos(product.id!!, false).enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("No se ha desactivado", t.message)
                        Toast.makeText(ctx, t.message + "No se ha desactivado!!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Toast.makeText(ctx, response.message() + " Producto desactivado!", Toast.LENGTH_SHORT).show()
                    }
                })
           }
            itemView.activar_producto.setOnClickListener{
                itemView.eliminar_productos.visibility=View.VISIBLE
                itemView.activar_producto.visibility=View.GONE
                productoControlador.desactivarProductos(product.id!!, true).enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("No se ha activado", t.message)
                        Toast.makeText(ctx, t.message + "No se ha activado!!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Toast.makeText(ctx, " Producto activado!", Toast.LENGTH_SHORT).show()
                    }
                })
            }

            itemView.crear_oferta.setOnClickListener{

                ProductoGlobal.guardarProducto(product)

                val action= ProductosProductorFragmentDirections.actionNavProductosProductorFragmentToOfertasFragment()

                itemView.findNavController().navigate(action)

            }
        }
    }




}