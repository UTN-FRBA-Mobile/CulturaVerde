package com.example.culturaverde.Ui.Alertasproductor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.AlertasproductorViewModel

class AlertasproductorFragment : Fragment() {

    private lateinit var alertasproductorViewModel: AlertasproductorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alertasproductorViewModel =
            ViewModelProviders.of(this).get(AlertasproductorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_alertasproductores, container, false)

        return root
    }
}