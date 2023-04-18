package com.example.rickandmortyapp.repository

import com.example.rickandmortyapp.models.models.character.CharacterList
import com.example.rickandmortyapp.models.models.location.LocationList
import com.example.rickandmortyapp.network.LocationApi.locationService
import retrofit2.Call

class RepositoryImpl: Repository {
    override fun getCharacter(ids: Array<Int>): Call<CharacterList> {
        return locationService.getCharacter(ids)
    }
    override fun getLocations(): Call<LocationList> {
        return locationService.getLocations()
    }
}