package com.example.culturaverde.ui.PrincipalProductor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R

class PrincipalproductorFragment : Fragment() {

    private lateinit var principalproductorViewModel: PrincipalproductorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        principalproductorViewModel =
            ViewModelProviders.of(this).get(PrincipalproductorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_principalproductores, container, false)

        return root
    }
}