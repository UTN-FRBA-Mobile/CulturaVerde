package com.example.culturaverde.Ui.Principalconsumidores.Categorias

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.culturaverde.R

class CategoriasFragment : Fragment() {

    companion object {
        fun newInstance() = CategoriasFragment()
    }

    private lateinit var viewModel: CategoriasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.categorias_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CategoriasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
