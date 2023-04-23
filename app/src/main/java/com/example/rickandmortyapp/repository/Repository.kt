package com.example.rickandmortyapp.repository

import com.example.rickandmortyapp.models.models.character.Character
import com.example.rickandmortyapp.models.models.location.LocationList
import retrofit2.Call

interface Repository {
    fun getLocations(): Call<LocationList>

    fun getCharacters(ids: List<Int>): Call<List<Character>>
}