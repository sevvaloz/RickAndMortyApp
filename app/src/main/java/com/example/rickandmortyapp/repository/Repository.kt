package com.example.rickandmortyapp.repository

import com.example.rickandmortyapp.models.models.character.CharacterList
import com.example.rickandmortyapp.models.models.character.Result
import com.example.rickandmortyapp.models.models.location.LocationList
import retrofit2.Call

interface Repository {
    fun getCharacters(ids: List<Int>): Call<List<Result>>
    fun getLocations(): Call<LocationList>
}