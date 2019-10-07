package com.example.culturaverde.ui.Login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.R
import com.example.culturaverde.ui.Login.LoginFragmentDirections
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

        botonPlayConsumidores.setOnClickListener{

            val action =
                LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableConsumidores()
            findNavController().navigate(action)

        }

        botonPlayProductores.setOnClickListener{

            val action =
                LoginFragmentDirections.actionLoginmainfragmentToMenudesplegableProductores()
            findNavController().navigate(action)
        }

    }

}
