package com.example.culturaverde.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.ElegirPuntoEntregaRetiroViewModel

class ElegirPuntoEntregaRetiroFragment : Fragment() {
    private lateinit var elegirpuntoentregaretiroViewModel: ElegirPuntoEntregaRetiroViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        elegirpuntoentregaretiroViewModel =
            ViewModelProviders.of(this).get(ElegirPuntoEntregaRetiroViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_elegirpuntoentregaretiro, container, false)

        return root
    }
}