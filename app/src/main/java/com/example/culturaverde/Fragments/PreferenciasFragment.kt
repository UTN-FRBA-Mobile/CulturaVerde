package com.example.culturaverde.Ui.PreferenciasConsumidor

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.PreferenciasViewModel

class PreferenciasFragment : Fragment() {

    companion object {
        fun newInstance() = PreferenciasFragment()
    }

    private lateinit var viewModel: PreferenciasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.preferencias_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PreferenciasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}