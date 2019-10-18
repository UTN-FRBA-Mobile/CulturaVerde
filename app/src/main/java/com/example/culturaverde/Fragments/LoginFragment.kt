package com.example.culturaverde.Ui.Login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.R
import kotlinx.android.synthetic.main.fragment_loginmain.*


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loginmain, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        botonIngreso.setOnClickListener{

            if(nombreUsuario.text.toString()=="Productor") {
                val action =
                    LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableProductores()
                findNavController().navigate(action)
            }

            if(nombreUsuario.text.toString()=="Consumidor"){

            val action =
                    LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableConsumidores()
                findNavController().navigate(action)

            }

        }

        botonRegistrar.setOnClickListener{

            val action = LoginFragmentDirections.actionLoginmainfragmentToRegistrar()
            findNavController().navigate(action)
        }

    }

}
