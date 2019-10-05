package com.example.culturaverde.ui.Principalconsumidores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrincipalConsumidoresViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Principal Consumidores"
    }
    val text: LiveData<String> = _text
}