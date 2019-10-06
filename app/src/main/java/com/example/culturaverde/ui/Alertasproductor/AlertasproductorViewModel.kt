package com.example.culturaverde.ui.Alertasproductor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlertasproductorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Alertas productores"
    }
    val text: LiveData<String> = _text
}