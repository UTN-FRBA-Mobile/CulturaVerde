package com.example.culturaverde.ui.Alertasproductor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R

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
        val textView: TextView = root.findViewById(R.id.text_alertasproductor)
        alertasproductorViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}