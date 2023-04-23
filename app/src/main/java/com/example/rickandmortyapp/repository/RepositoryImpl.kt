package com.example.rickandmortyapp.repository

import com.example.rickandmortyapp.models.models.character.Character
import com.example.rickandmortyapp.models.models.location.LocationList
import com.example.rickandmortyapp.network.AppApi.locationService
import retrofit2.Call

class RepositoryImpl: Repository {
    override fun getLocations(): Call<LocationList> {
        return locationService.getLocations()
    }
    override fun getCharacters(ids: List<Int>): Call<List<Character>> {
        return locationService.getCharacters(ids)
    }
}