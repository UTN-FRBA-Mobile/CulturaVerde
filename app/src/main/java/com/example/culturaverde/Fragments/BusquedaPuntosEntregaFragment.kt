package com.example.culturaverde.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.culturaverde.Adapters.PuntosEntregaAdapter
import com.example.culturaverde.Controllers.PuntosentregaControlador
import com.example.culturaverde.Models.PuntosEntrega
import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.BusquedaPuntosEntregaViewModel
import kotlinx.android.synthetic.main.fragment_busquedapuntosentrega.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusquedaPuntosEntregaFragment : Fragment() {

    companion object {
        fun newInstance() = BusquedaPuntosEntregaFragment()
    }

    private lateinit var viewModel: BusquedaPuntosEntregaViewModel
    private lateinit var puntosEntregaControlador: PuntosentregaControlador
    private lateinit var puntosEntregaAdapter: PuntosEntregaAdapter
    private var puntosentrega = listOf<PuntosEntrega>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_busquedapuntosentrega, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BusquedaPuntosEntregaViewModel::class.java)
    // TODO: Use the ViewModel

    puntosEntregaControlador =
    APIConfig.getRetrofitClient(requireContext()).create(PuntosentregaControlador::class.java)

    swipeRefreshLayout4.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.color_verde))

    swipeRefreshLayout4.isRefreshing = true

    recycler_puntosentrega.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        getpuntosdeentrega()

}

    fun getpuntosdeentrega() {
        puntosEntregaControlador.obtenerpuntosdeentrega()
        .enqueue(object : Callback<List<PuntosEntrega>> {
            override fun onFailure(call: Call<List<PuntosEntrega>>, t: Throwable) {


                Toast.makeText(requireContext(), "Ocurrió un error inesperado, intentá nuevamente", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(
                call: Call<List<PuntosEntrega>>,
                response: Response<List<PuntosEntrega>>
            ) {

                swipeRefreshLayout4.isRefreshing = false
                swipeRefreshLayout4.isEnabled = false

                if(response!=null) {

                    puntosentrega = response.body()!!

                    puntosEntregaAdapter = PuntosEntregaAdapter(requireContext(), puntosentrega)
                    recycler_puntosentrega.adapter = puntosEntregaAdapter
                    puntosEntregaAdapter.notifyDataSetChanged()
                }
            }

        })
}

}