package com.example.culturaverde.Activities

import android.content.Intent
import android.graphics.PorterDuff

import android.os.Bundle
import androidx.core.content.ContextCompat
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.culturaverde.Adapters.ProductAdapter
import com.example.culturaverde.Classes.ShoppingCart
import com.example.culturaverde.Models.ProductoProductor
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.Controllers.ProductosControlador
import kotlinx.android.synthetic.main.activity_resultado_busqueda.*
import retrofit2.Call
import io.paperdb.Paper
import retrofit2.Response
import android.view.View
import com.example.culturaverde.Classes.CategoriaProductoGlobal
import com.example.culturaverde.Classes.ProductorGlobal
import com.example.culturaverde.Models.ProductorMaps



class ResultadoBusqueda : AppCompatActivity() {

    private lateinit var productosControlador: ProductosControlador
    private lateinit var productAdapter: ProductAdapter

    private var products = listOf<ProductoProductor>()
//    val APIConfig = APIConfig.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resultado_busqueda)

        setSupportActionBar(toolbar)

        toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.common_google_signin_btn_text_dark_default), PorterDuff.Mode.SRC_ATOP)

        toolbar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                finish()


            }
        })

        productosControlador = APIConfig.getRetrofitClient(this).create(ProductosControlador::class.java)


        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.color_verde))

        swipeRefreshLayout.isRefreshing = true

//        val layoutManager = StaggeredGridLayoutManager(this, Lin)

        products_recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        cart_size.text = ShoppingCart.getShoppingCartSize().toString()

        getProductosBusqueda()


        showCart.setOnClickListener {

            startActivity(Intent(this, ShoppingCartActivity::class.java))
        }
    }

    public override fun onResume() {
        super.onResume()

        cart_size.text = ShoppingCart.getShoppingCartSize().toString()

    }

    fun getProductosBusqueda() {

        var id_productor= ProductorGlobal.getProductor().id

        var busqueda = CategoriaProductoGlobal.getProducto().tipo

        if (id_productor != null) {

            productosControlador.getProductosBusquedaProductor(id_productor)
                .enqueue(object : retrofit2.Callback<List<ProductoProductor>> {
                    override fun onFailure(call: Call<List<ProductoProductor>>, t: Throwable) {

                        Toast.makeText(this@ResultadoBusqueda, "Ocurrió un error inesperado, intentá nuevamente", Toast.LENGTH_SHORT).show()

                    }

                    override fun onResponse(
                        call: Call<List<ProductoProductor>>,
                        response: Response<List<ProductoProductor>>
                    ) {

                        ProductorGlobal.guardarProductor(ProductorMaps())

                        swipeRefreshLayout.isRefreshing = false
                        swipeRefreshLayout.isEnabled = false

                        products = response.body()!!

                        productAdapter = ProductAdapter(this@ResultadoBusqueda, products)

                        products_recyclerview.adapter = productAdapter
                        productAdapter.notifyDataSetChanged()

                    }

                })





        }else {

            productosControlador.getProductosBusqueda(busqueda)
                .enqueue(object : retrofit2.Callback<List<ProductoProductor>> {
                    override fun onFailure(call: Call<List<ProductoProductor>>, t: Throwable) {

                        print(t.message)
                        Log.d("Data error", t.message!!)
                        Toast.makeText(this@ResultadoBusqueda, t.message, Toast.LENGTH_SHORT).show()

                    }

                    override fun onResponse(
                        call: Call<List<ProductoProductor>>,
                        response: Response<List<ProductoProductor>>
                    ) {

                        swipeRefreshLayout.isRefreshing = false
                        swipeRefreshLayout.isEnabled = false
                        if(response!=null) {
                            products = response.body()!!

                            productAdapter = ProductAdapter(this@ResultadoBusqueda, products)

                            products_recyclerview.adapter = productAdapter
                            productAdapter.notifyDataSetChanged()
                        }
                    }

                })
        }
    }

}
