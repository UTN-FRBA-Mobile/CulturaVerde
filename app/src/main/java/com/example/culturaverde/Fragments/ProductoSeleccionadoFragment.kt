package com.example.culturaverde.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.ProductoSeleccionadoViewModel

class ProductoSeleccionadoFragment : Fragment() {

    companion object {
        fun newInstance() = ProductoSeleccionadoFragment()
    }

    private lateinit var viewModel: ProductoSeleccionadoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.producto_seleccionado_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductoSeleccionadoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
