package com.example.culturaverde.Fragments

import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.culturaverde.Adapters.ModificarProductoAdapter
import com.example.culturaverde.Classes.ProductoGlobal
import com.example.culturaverde.Controllers.ProductosControlador
import com.example.culturaverde.Models.Producto
import com.example.culturaverde.Models.ProductoProductor
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.ProductoModificarViewModel
import kotlinx.android.synthetic.main.producto_modificar_fragment.*
import kotlinx.android.synthetic.main.productos_productor_fragment.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductoModificarFragment : Fragment() {



    companion object {
        fun newInstance() = ProductoModificarFragment()
    }

    private lateinit var viewModel: ProductoModificarViewModel
    private lateinit var modificarProductoAdapter: ModificarProductoAdapter
    private lateinit var productosControlador: ProductosControlador
    val product: ProductoProductor=ProductoGlobal.getProducto()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.producto_modificar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductoModificarViewModel::class.java)
        // TODO: Use the ViewModel


        visorFotosProducto.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        modificarProductoAdapter = ModificarProductoAdapter(requireContext(), product.imagenes)

        visorFotosProducto.adapter = modificarProductoAdapter
        modificarProductoAdapter.notifyDataSetChanged()

        modificarPrecioProducto.text = SpannableStringBuilder(product.precio.toString())
        modificarStockProducto.text  = SpannableStringBuilder(product.stock.toString())
        modificarTituloProducto.text = SpannableStringBuilder(product.titulo.toString())
        modificarDescripcion.text = SpannableStringBuilder(product.descripcion.toString())

        botonModificarProducto.setOnClickListener{

            guardarDatosProducto()
        }
    }


    fun guardarDatosProducto(){

        productosControlador =
            APIConfig.getRetrofitClient(requireContext()).create(ProductosControlador::class.java)

        val paramObject = JSONObject()
        if ( modificarPrecioProducto.text.toString() != "" &&
            modificarStockProducto.text.toString() != "" &&
            modificarTituloProducto.text.toString()!= "" &&
            modificarDescripcion.text.toString()!= ""
        ) {
            paramObject.put("precio", modificarPrecioProducto.text.toString())
            paramObject.put("stock", modificarStockProducto.text.toString())
            paramObject.put("titulo", modificarTituloProducto.text.toString())
            paramObject.put("descripcion", modificarDescripcion.text.toString())
            //paramObject.put("fecha_vencimiento", product.fecha_vencimiento)
            paramObject.put("tiempo_preparacion", product.tiempo_preparacion)
            paramObject.put("contenido", product.contenido)
           // paramObject.put("imagenes", product.imagenes)

            productosControlador.editarDatosProducto(paramObject.toString(), product.id!!)
                .enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                       Toast.makeText(
                            requireContext(),
                            "No se ha podido modificar el usuario",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Toast.makeText(
                            requireContext(),
                            "Modificación exitosa!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
        else{Toast.makeText(requireContext(),"Debe completar los campos vacíos!",Toast.LENGTH_SHORT).show()}
    }
}
