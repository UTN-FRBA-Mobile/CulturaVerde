package com.example.culturaverde.Ui.PreferenciasConsumidor

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.ListadoPreferenciasViewModel

class ListadoPreferenciasFragment : Fragment() {

    companion object {
        fun newInstance() = ListadoPreferenciasFragment()
    }

    private lateinit var viewModel: ListadoPreferenciasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.listado_preferencias_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListadoPreferenciasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
