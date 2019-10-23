package com.example.culturaverde.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.culturaverde.R
import com.example.culturaverde.ViewModels.CreartipousuarioViewModel
import kotlinx.android.synthetic.main.fragment_creartipousuario.*

class CreartipousuarioFragment : Fragment() {

    private lateinit var creartipousuarioViewModel: CreartipousuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        creartipousuarioViewModel =
            ViewModelProviders.of(this).get(CreartipousuarioViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_creartipousuario, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tipoproductor.setOnClickListener{
            val action =
                CreartipousuarioFragmentDirections.actionCreartipousuarioToCrearProductorFragment()
                findNavController().navigate(action)
            }

        tipoconsumidor.setOnClickListener{
            val action =
                CreartipousuarioFragmentDirections.actionCreartipousuarioToCrearConsumidorFragment()
            findNavController().navigate(action)
        }
        }


    }