package com.example.rickandmortyapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.LocationData.Location
import com.example.rickandmortyapp.CharacterData.Character

class ViewModel: ViewModel() {

    private val characters = MutableLiveData<List<Character>>()
    val charactersData: LiveData<List<Character>> get() = characters

    private val locations = MutableLiveData<List<Location>>()
    val locationsData: LiveData<List<Location>> get() = locations





}