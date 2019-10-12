package com.example.culturaverde.ui.Geolocalizacion

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.culturaverde.R

class GeoFragment: Fragment() {
    companion object {
        fun newInstance() = GeoFragment()
    }

    private lateinit var viewModel: GeoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_geolocalizacion, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GeoViewModel::class.java)
        // TODO: Use the ViewModel
    }
}