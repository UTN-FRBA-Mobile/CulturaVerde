package com.example.culturaverde.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.culturaverde.Adapters.CalificacionesAdapter
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.Controllers.CalificacionesControlador
import com.example.culturaverde.Models.Calificacion

import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.CalificacionesViewModel
import kotlinx.android.synthetic.main.calificaciones_fragment.*
import retrofit2.Call
import retrofit2.Response

class CalificacionesFragment : Fragment() {

    companion object {
        fun newInstance() = CalificacionesFragment()
    }

    private lateinit var calificacionesControlador: CalificacionesControlador
    private lateinit var calificacionesAdapter: CalificacionesAdapter
    private var calificaciones = listOf<Calificacion>()
    private lateinit var viewModel: CalificacionesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calificaciones_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CalificacionesViewModel::class.java)
        // TODO: Use the ViewModel

        calificacionesControlador =
            APIConfig.getRetrofitClient(requireContext()).create(CalificacionesControlador::class.java)

        swipeRefreshLayout5.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.color_verde))

        swipeRefreshLayout5.isRefreshing = true

        recycler_calificaciones.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        getCalificaciones()
    }

    fun getCalificaciones() {
        calificacionesControlador.getCalificaciones(UsuarioGlobal.getUsuario()!!.id)
            .enqueue(object : retrofit2.Callback<List<Calificacion>> {
                override fun onFailure(call: Call<List<Calificacion>>, t: Throwable) {

                    Toast.makeText(
                        requireContext(),
                        "Ocurrió un error inesperado, intentá nuevamente",
                        Toast.LENGTH_SHORT
                    ).show()

                }

                override fun onResponse(
                    call: Call<List<Calificacion>>,
                    response: Response<List<Calificacion>>
                ) {

                    swipeRefreshLayout5.isRefreshing = false
                    swipeRefreshLayout5.isEnabled = false

                    if (response != null) {
                        calificaciones = response.body()!!

                        calificacionesAdapter = CalificacionesAdapter(requireContext(), calificaciones)
                        recycler_calificaciones.adapter = calificacionesAdapter
                        calificacionesAdapter.notifyDataSetChanged()
                    }
                }

            })

    }

}
