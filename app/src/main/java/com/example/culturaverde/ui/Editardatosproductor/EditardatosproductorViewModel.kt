package com.example.culturaverde.Ui.Editardatosproductor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditardatosproductorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "editar datos productores"
    }
    val text: LiveData<String> = _text
}