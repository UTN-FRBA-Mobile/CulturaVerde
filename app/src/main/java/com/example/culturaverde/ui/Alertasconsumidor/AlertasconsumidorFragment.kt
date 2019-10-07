package com.example.culturaverde.ui.Alertasconsumidor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R

class AlertasconsumidorFragment : Fragment() {

    private lateinit var alertasconsumidorViewModel: AlertasconsumidorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alertasconsumidorViewModel =
            ViewModelProviders.of(this).get(AlertasconsumidorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_alertasconsumidores, container, false)
        val textView: TextView = root.findViewById(R.id.textViewAlertasConsumidor)
        alertasconsumidorViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}