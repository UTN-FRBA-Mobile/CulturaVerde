package com.example.culturaverde.ui.Editardatosconsumidor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R

class EditardatosconsumidorFragment : Fragment() {

    private lateinit var editardatosconsumidorViewModel: EditardatosconsumidorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editardatosconsumidorViewModel =
            ViewModelProviders.of(this).get(EditardatosconsumidorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_editardatosconsumidores, container, false)
        val textView: TextView = root.findViewById(R.id.text_editardatosconsumidor)
        editardatosconsumidorViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}