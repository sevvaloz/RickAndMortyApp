package com.example.rickandmortyapp.network

import com.example.rickandmortyapp.models.models.character.Result
import com.example.rickandmortyapp.models.models.location.LocationList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationService {

    @GET("location")
    fun getLocations(): Call<LocationList>

    @GET("character/{ids}/")
    fun getCharacters(@Path("ids") ids: List<Int>): Call<List<Result>>
}