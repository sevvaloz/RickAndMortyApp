package com.example.rickandmortyapp.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppApi {

    private val productGson: Gson by lazy {
        GsonBuilder().create()
    }

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    val locationService: AppService by lazy {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(productGson))
            .build()

        retrofit.create(AppService::class.java)
    }

}