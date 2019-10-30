package com.example.culturaverde.Ui.Principalconsumidores.Categorias

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.culturaverde.Adapters.CategoriasAdapter
import com.example.culturaverde.Controllers.CategoriasControlador
import com.example.culturaverde.Models.Producto

import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.CategoriasViewModel
import kotlinx.android.synthetic.main.categorias_fragment.*
import kotlinx.android.synthetic.main.reservas_fragment.*
import retrofit2.Call
import retrofit2.Response

class CategoriasFragment : Fragment() {

    companion object {
        fun newInstance() = CategoriasFragment()
    }
    private lateinit var viewModel: CategoriasViewModel
    private lateinit var categoriasControlador: CategoriasControlador
    private lateinit var categoriasAdapter: CategoriasAdapter
    private var categorias = listOf<Producto>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.categorias_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CategoriasViewModel::class.java)
        // TODO: Use the ViewModel


        categoriasControlador =
            APIConfig.getRetrofitClient(requireContext()).create(CategoriasControlador::class.java)

        swipeRefreshLayout4.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.color_verde))

        swipeRefreshLayout4.isRefreshing = true

        recycler_categorias.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        getCategorias()

    }


    fun getCategorias() {
        categoriasControlador.getCategorias()
            .enqueue(object : retrofit2.Callback<List<Producto>> {
                override fun onFailure(call: Call<List<Producto>>, t: Throwable) {


                    Toast.makeText(requireContext(), "Ocurrió un error inesperado, intentá nuevamente", Toast.LENGTH_SHORT).show()

                }

                override fun onResponse(
                    call: Call<List<Producto>>,
                    response: Response<List<Producto>>
                ) {

                    swipeRefreshLayout4.isRefreshing = false
                    swipeRefreshLayout4.isEnabled = false

                    categorias = response.body()!!

                    categoriasAdapter = CategoriasAdapter(requireContext(), categorias)
                    recycler_categorias.adapter = categoriasAdapter
                    categoriasAdapter.notifyDataSetChanged()

                }

            })
    }
}
