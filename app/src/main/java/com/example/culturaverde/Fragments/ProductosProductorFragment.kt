package com.example.culturaverde.Ui.PrincipalProductor.Productos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.culturaverde.Adapters.ProductoProductorAdapter
import com.example.culturaverde.Controllers.ProductosControlador
import com.example.culturaverde.Models.ProductoProductor
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.ProductosProductorViewModel
import kotlinx.android.synthetic.main.productos_productor_fragment.*
import retrofit2.Call
import retrofit2.Response

class ProductosProductorFragment : Fragment() {

    companion object {
        fun newInstance() = ProductosProductorFragment()
    }

    private lateinit var productosControlador: ProductosControlador
    private lateinit var productAdapter: ProductoProductorAdapter
    private var products = listOf<ProductoProductor>()
    private lateinit var viewModel: ProductosProductorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.productos_productor_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductosProductorViewModel::class.java)
        // TODO: Use the ViewModel

         productosControlador =
             APIConfig.getRetrofitClient(requireContext()).create(ProductosControlador::class.java)

        swipeRefreshLayout2.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.color_verde))

        swipeRefreshLayout2.isRefreshing = true

          recycler_productos.layoutManager = LinearLayoutManager(requireContext())

          getProducts()
    }

    fun getProducts() {
        productosControlador.getProducts().enqueue(object : retrofit2.Callback<List<ProductoProductor>> {
            override fun onFailure(call: Call<List<ProductoProductor>>, t: Throwable) {

                print(t.message)
                Log.d("Data error", t.message)
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<ProductoProductor>>, response: Response<List<ProductoProductor>>) {

                swipeRefreshLayout2.isRefreshing = false
                swipeRefreshLayout2.isEnabled = false

                products = response.body()!!

                productAdapter = ProductoProductorAdapter(requireContext(), products)

                recycler_productos.adapter = productAdapter
                productAdapter.notifyDataSetChanged()

            }

        })
    }

}
