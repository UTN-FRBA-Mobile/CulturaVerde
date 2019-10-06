package com.example.culturaverde.ui.PrincipalProductor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrincipalproductorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Principal productores"
    }
    val text: LiveData<String> = _text
}