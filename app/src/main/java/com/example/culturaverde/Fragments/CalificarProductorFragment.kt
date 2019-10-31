package com.example.culturaverde.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.Classes.ReservaGlobal
import com.example.culturaverde.Controllers.CalificacionesControlador

import com.example.culturaverde.R
import com.example.culturaverde.Services.APIConfig
import com.example.culturaverde.ViewModels.CalificarProductorViewModel
import kotlinx.android.synthetic.main.calificar_productor_fragment.*
import kotlinx.android.synthetic.main.calificar_productor_fragment.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CalificarProductorFragment : Fragment() {

    private lateinit var  calificacionesControlador: CalificacionesControlador

    companion object {
        fun newInstance() = CalificarProductorFragment()
    }

    private lateinit var viewModel: CalificarProductorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calificar_productor_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CalificarProductorViewModel::class.java)


        view!!.buttonGuardarCalificacion.setOnClickListener{

            val paramObject = JSONObject()

            val fechaHoy = Date()

            paramObject.put("reserva", ReservaGlobal.getReserva())
            paramObject.put("valor", calificacion.rating)
            paramObject.put("comentario", comentarios.text)
            paramObject.put("fecha_calificacion", java.sql.Date(fechaHoy.getTime()))

            calificar(paramObject)

        }

    }

    fun calificar(paramObject:JSONObject){

        calificacionesControlador =
            APIConfig.getRetrofitClient(requireContext()).create(CalificacionesControlador::class.java)

        calificacionesControlador.guardarCalificacion(ReservaGlobal.getReserva().id,paramObject.toString())
            .enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {

                    Toast.makeText(requireContext(), "Ocurrió algún problema, intentá nuevamente", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {

                    Toast.makeText(requireContext(), "Calificación guardada correctamente!", Toast.LENGTH_SHORT).show()

                }


            })


    }

}
