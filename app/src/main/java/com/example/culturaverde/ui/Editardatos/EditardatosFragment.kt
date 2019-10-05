package com.example.culturaverde.ui.Editardatos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.culturaverde.R

class EditardatosFragment : Fragment() {

    private lateinit var editardatosViewModel: EditardatosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editardatosViewModel =
            ViewModelProviders.of(this).get(EditardatosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_editardatos, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        editardatosViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}