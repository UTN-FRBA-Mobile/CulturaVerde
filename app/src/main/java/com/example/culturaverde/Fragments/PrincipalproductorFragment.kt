package com.example.culturaverde.Ui.PrincipalProductor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.culturaverde.Classes.UsuarioGlobal
import com.example.culturaverde.R
import com.example.culturaverde.Ui.Login.LoginFragment
import com.example.culturaverde.Ui.Login.LoginFragmentDirections
import com.example.culturaverde.ViewModels.PrincipalproductorViewModel
import kotlinx.android.synthetic.main.fragment_principalproductores.*


class PrincipalproductorFragment : Fragment() {

    private lateinit var principalproductorViewModel: PrincipalproductorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        principalproductorViewModel =
            ViewModelProviders.of(this).get(PrincipalproductorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_principalproductores, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            imagenProductos.setOnClickListener {

                val action =
                    PrincipalproductorFragmentDirections.actionNavPrincipalproductoresToProductosProductorFragment()

                findNavController().navigate(action)
            }

            imagenReservas.setOnClickListener { view ->

                val action =
                    PrincipalproductorFragmentDirections.actionNavPrincipalproductoresToReservasFragment()

                findNavController().navigate(action)

            }


    }
}