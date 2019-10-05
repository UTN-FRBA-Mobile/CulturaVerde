package com.example.culturaverde.ui.Editardatos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditardatosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Editar Datos"
    }
    val text: LiveData<String> = _text
}