package com.example.culturaverde.Services

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import retrofit2.converter.scalars.ScalarsConverterFactory

object APIConfig {


    val BASE_URL = "http://192.168.0.133:8080/"

    private var retrofit: Retrofit? = null

    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofitClient(context: Context? = null): Retrofit {

        val okHttpClient = OkHttpClient.Builder().build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }

}