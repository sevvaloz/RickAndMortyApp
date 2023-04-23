package com.example.rickandmortyapp.network

import com.example.rickandmortyapp.models.models.character.Character
import com.example.rickandmortyapp.models.models.location.LocationList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AppService {
    @GET("location")
    fun getLocations(): Call<LocationList>

    @GET("character/{ids}")
    fun getCharacters(@Path("ids") ids: List<Int>): Call<List<Character>>

    @GET("character/{id}")
    fun getSingleCharacter(@Path("id") id: Int): Call<Character>
}