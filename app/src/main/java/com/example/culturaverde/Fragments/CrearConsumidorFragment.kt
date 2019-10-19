package com.example.culturaverde.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.CrearConsumidorViewModel

class CrearConsumidorFragment: Fragment() {

    private lateinit var crearconsumidorViewModel: CrearConsumidorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        crearconsumidorViewModel =
            ViewModelProviders.of(this).get(CrearConsumidorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_crearusuarioconsumidor, container, false)

        return root
    }
}