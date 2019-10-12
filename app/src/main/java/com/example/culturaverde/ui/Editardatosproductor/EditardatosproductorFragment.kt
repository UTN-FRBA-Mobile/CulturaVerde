package com.example.culturaverde.Ui.Editardatosproductor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R

class EditardatosproductorFragment : Fragment() {

    private lateinit var editardatosproductorViewModel: EditardatosproductorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editardatosproductorViewModel =
            ViewModelProviders.of(this).get(EditardatosproductorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_editardatosproductores, container, false)
        val textView: TextView = root.findViewById(R.id.text_editarproductor)
        editardatosproductorViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}