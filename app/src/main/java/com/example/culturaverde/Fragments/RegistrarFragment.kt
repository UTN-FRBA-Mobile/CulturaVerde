package com.example.culturaverde.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.RegistrarViewModel
import androidx.lifecycle.Observer

class RegistrarFragment : Fragment() {

    private lateinit var registrarViewModel: RegistrarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registrarViewModel =
            ViewModelProviders.of(this).get(RegistrarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_registrar, container, false)

        return root
    }
}