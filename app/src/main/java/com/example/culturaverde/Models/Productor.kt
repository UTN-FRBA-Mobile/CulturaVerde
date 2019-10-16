package com.example.culturaverde.Models

import com.google.gson.annotations.SerializedName

class Productor {

    @SerializedName("id")
    var id: Long? = null

    var usuario: Usuario? = null

    var razon_social: String? = null

}