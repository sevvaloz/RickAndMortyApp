package com.example.rickandmortyapp.repository

import com.example.rickandmortyapp.models.models.character.CharacterList
import com.example.rickandmortyapp.models.models.location.LocationList
import retrofit2.Call

interface Repository {
    fun getCharacter(ids: Array<Int>): Call<CharacterList>
    fun getLocations(): Call<LocationList>
}