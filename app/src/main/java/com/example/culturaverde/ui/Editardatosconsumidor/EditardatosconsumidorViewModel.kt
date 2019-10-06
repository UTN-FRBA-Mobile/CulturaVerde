package com.example.culturaverde.ui.Editardatosconsumidor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditardatosconsumidorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Editar Datos consumidores"
    }
    val text: LiveData<String> = _text
}