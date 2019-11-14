package com.example.culturaverde.Ui.Reservas

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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.culturaverde.Adapters.DetalleReservaAdapter
import com.example.culturaverde.Adapters.ReservasAdapter
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.Controllers.ReservasControlador
import com.example.culturaverde.Models.Reserva

import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.ReservasViewModel
import kotlinx.android.synthetic.main.reservas_fragment.*
import kotlinx.android.synthetic.main.reservas_item.*
import retrofit2.Call
import retrofit2.Response

class ReservasFragment : Fragment() {

    companion object {
        fun newInstance() = ReservasFragment()
    }

    private lateinit var reservasControlador: ReservasControlador
    private lateinit var reservasAdapter: ReservasAdapter
    private var reservas = listOf<Reserva>()
    private lateinit var viewModel: ReservasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reservas_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReservasViewModel::class.java)
        // TODO: Use the ViewModel

        reservasControlador =
            APIConfig.getRetrofitClient(requireContext()).create(ReservasControlador::class.java)

        swipeRefreshLayout3.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.color_verde))

        swipeRefreshLayout3.isRefreshing = true

        recycler_reservas.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        getReservas()


    }


    fun getReservas() {
        reservasControlador.getReservas(UsuarioGlobal.getUsuario().id)
            .enqueue(object : retrofit2.Callback<List<Reserva>> {
                override fun onFailure(call: Call<List<Reserva>>, t: Throwable) {

                    Toast.makeText(requireContext(), "Ocurrió un error inesperado, intentá nuevamente", Toast.LENGTH_SHORT).show()

                }

                override fun onResponse(
                    call: Call<List<Reserva>>,
                    response: Response<List<Reserva>>
                ) {

                    swipeRefreshLayout3.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.color_verde))

                    swipeRefreshLayout3.isRefreshing = false
                    swipeRefreshLayout3.isEnabled = false

                    if(response!=null) {
                        reservas = response.body()!!

                        reservasAdapter = ReservasAdapter(requireContext(), reservas)
                        recycler_reservas.adapter = reservasAdapter
                        reservasAdapter.notifyDataSetChanged()
                    }
                }

            })
    }
}
