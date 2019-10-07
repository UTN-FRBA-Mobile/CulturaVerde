package com.example.culturaverde.ui.Alertasconsumidor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlertasconsumidorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Alertas"
    }
    val text: LiveData<String> = _text
}