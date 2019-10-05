package com.example.culturaverde.ui.Principalconsumidores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R

class PrincipalConsumidoresFragment : Fragment() {

    private lateinit var principalConsumidoresViewModel: PrincipalConsumidoresViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        principalConsumidoresViewModel =
            ViewModelProviders.of(this).get(PrincipalConsumidoresViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_principalconsumidores, container, false)
        val textView: TextView = root.findViewById(R.id.text_principalconsumidores)
        principalConsumidoresViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}