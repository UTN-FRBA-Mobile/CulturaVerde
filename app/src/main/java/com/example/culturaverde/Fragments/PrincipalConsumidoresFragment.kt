package com.example.culturaverde.Ui.Principalconsumidores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.culturaverde.Activities.ResultadoBusqueda
import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.PrincipalConsumidoresViewModel
import kotlinx.android.synthetic.main.fragment_principalconsumidores.*
import kotlinx.android.synthetic.main.fragment_principalconsumidores.imagenReservas
import kotlinx.android.synthetic.main.fragment_principalproductores.*


class PrincipalConsumidoresFragment : Fragment() {

    private lateinit var principalConsumidoresViewModel: PrincipalConsumidoresViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        principalConsumidoresViewModel =
            ViewModelProviders.of(this).get(PrincipalConsumidoresViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_principalconsumidores, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        imagenCategorias.setOnClickListener { view ->

            view.findNavController().navigate(R.id.action_nav_principalconsumidores_to_nav_categoriasFragment2)
        }

        imagenReservas.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_nav_principalconsumidores_to_nav_reservasFragment)
        }

        imagenGeolocalizacion.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_nav_principalconsumidores_to_nav_geolocalizacionFragment)
        }

    }

}