package com.example.culturaverde.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.culturaverde.Classes.ReservaGlobal
import com.example.culturaverde.Classes.UsuarioGlobal

import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.EstadoReservaViewModel
import kotlinx.android.synthetic.main.estado_reserva_fragment.*
import kotlinx.android.synthetic.main.estado_reserva_fragment.view.*

class EstadoReservaFragment : Fragment() {

    companion object {
        fun newInstance() = EstadoReservaFragment()
    }

    private lateinit var viewModel: EstadoReservaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.estado_reserva_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EstadoReservaViewModel::class.java)
        // TODO: Use the ViewModel

        view!!.radioButton1.setVisibility(View.GONE)
        view!!.radioButton2.setVisibility(View.GONE)
        view!!.radioButton3.setVisibility(View.GONE)
        view!!.radioButton4.setVisibility(View.GONE)
        view!!.radioButton5.setVisibility(View.GONE)

        if (UsuarioGlobal.getUsuario().rol == "Consumidor") {

            view!!.radioButton4.setVisibility(View.VISIBLE)
            view!!.radioButton5.setVisibility(View.VISIBLE)

            return

        }

            view!!.radioButton1.setVisibility(View.VISIBLE)
            view!!.radioButton2.setVisibility(View.VISIBLE)
            view!!.radioButton3.setVisibility(View.VISIBLE)
            view!!.radioButton4.setVisibility(View.VISIBLE)
            view!!.radioButton5.setVisibility(View.VISIBLE)



        buttonGuardarEstado.setOnClickListener{




        }

    }

}
