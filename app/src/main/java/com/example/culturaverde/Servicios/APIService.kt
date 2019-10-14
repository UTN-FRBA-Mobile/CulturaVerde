package com.example.culturaverde.Servicios

import com.example.culturaverde.Models.Product
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("bestRated")
    fun getProducts(
    ): Call<List<Product>>

}