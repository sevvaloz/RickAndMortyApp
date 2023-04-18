package com.example.rickandmortyapp.repository

import com.example.rickandmortyapp.models.models.character.CharacterList
import com.example.rickandmortyapp.models.models.character.Result
import com.example.rickandmortyapp.models.models.location.LocationList
import com.example.rickandmortyapp.network.LocationApi.locationService
import retrofit2.Call

class RepositoryImpl: Repository {
    override fun getCharacters(ids: List<Int>): Call<List<Result>> {
        return locationService.getCharacters(ids)
    }

    override fun getLocations(): Call<LocationList> {
        return locationService.getLocations()
    }
}