package com.example.culturaverde.ui.Alertas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R

class AlertasFragment : Fragment() {

    private lateinit var alertasViewModel: AlertasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alertasViewModel =
            ViewModelProviders.of(this).get(AlertasViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_alertas, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        alertasViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}