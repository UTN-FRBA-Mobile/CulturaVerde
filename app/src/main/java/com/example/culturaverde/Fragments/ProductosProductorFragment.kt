package com.example.culturaverde.Ui.PrincipalProductor.Productos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.ProductosProductorViewModel

class ProductosProductorFragment : Fragment() {

    companion object {
        fun newInstance() = ProductosProductorFragment()
    }

    private lateinit var viewModel: ProductosProductorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.productos_productor_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductosProductorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
