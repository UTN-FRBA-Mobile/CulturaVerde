package com.example.culturaverde.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.ResumenReservasViewModel

class ResumenReservasFragment: Fragment() {

    private lateinit var resumenreservasViewModel: ResumenReservasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        resumenreservasViewModel =
            ViewModelProviders.of(this).get(ResumenReservasViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_resumenreservas, container, false)

        return root
    }
}