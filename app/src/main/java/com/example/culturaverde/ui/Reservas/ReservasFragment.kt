package com.example.culturaverde.Ui.Reservas

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.culturaverde.R

class ReservasFragment : Fragment() {

    companion object {
        fun newInstance() = ReservasFragment()
    }

    private lateinit var viewModel: ReservasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reservas_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReservasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
