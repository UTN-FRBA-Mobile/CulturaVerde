package com.example.culturaverde.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Crea tu cuenta en Cultura Verde"
    }

    val text: LiveData<String> = _text

}
