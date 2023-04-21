package com.example.rickandmortyapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.models.models.character.Character
import com.example.rickandmortyapp.models.models.location.LocationList
import com.example.rickandmortyapp.repository.Repository
import com.example.rickandmortyapp.repository.RepositoryImpl
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(): ViewModel() {

    private val locations = MutableLiveData<LocationList>()
    val locationsData: LiveData<LocationList> get() = locations

    private val characters = MutableLiveData<List<Character>>()
    val charactersData: LiveData<List<Character>> get() = characters

    /*private val singleCharacter = MutableLiveData<Character>()
    val singleCharacterData: LiveData<Character> get() = singleCharacter*/

    private lateinit var repository: Repository

    fun setRepo(repositoryImlp: RepositoryImpl){
        repository = repositoryImlp
    }

    fun getLocations(){
        viewModelScope.launch {
            repository.getLocations().enqueue(object : Callback<LocationList?> {
                override fun onResponse(call: Call<LocationList?>, response: Response<LocationList?>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.d("TAG", "ServiceSuccess1")
                            locations.postValue(it)
                        }?: kotlin.run {
                            Log.d("TAG", "EmptyBody1")
                        }
                    } else {
                        Log.d("TAG", "ServiceFailed. ResponseMessage1: ${response.message()}")
                        Log.d("ServiceFailedCode", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<LocationList?>, t: Throwable) {
                    Log.d("TAG", "ServiceFailed. Message1: ${t.message}")
                }
            })
        }
    }

    fun getCharacters(_ids: List<Int>){
        viewModelScope.launch {
            repository.getCharacters(ids = _ids).enqueue(object : Callback<List<Character>> {
                override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.d("TAG", "ServiceSuccess2")
                            characters.postValue(it)
                        }?: kotlin.run {
                            Log.d("TAG", "EmptyBody2")
                        }
                    } else {
                        Log.d("TAG", "ServiceFailed. ResponseMessage2: ${response.message()}")
                        Log.d("ServiceFailedCode", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                    Log.d("TAG", "ServiceFailed. Message2: ${t.message}")
                }
            })
        }
    }

/*    fun getSingleCharacter(_id: Int){
        viewModelScope.launch {
            repository.getSingleCharacter(id = _id).enqueue(object : Callback<Character?> {
                override fun onResponse(call: Call<Character?>, response: Response<Character?>) {
                    if(response.isSuccessful) {
                        response.body()?.let {
                            Log.d("TAG", "ServiceSuccess3")
                            //singleCharacter.postValue(it)
                            singleCharacter.value = response.body()
                        }?: kotlin.run {
                            Log.d("TAG", "EmptyBody3")
                        }
                    } else {
                        Log.d("TAG", "ServiceFailed. ResponseMessage3: ${response.message()}")
                        Log.d("ServiceFailedCode", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<Character?>, t: Throwable) {
                    Log.d("TAG", "ServiceFailed. Message3: ${t.message}")
                }
            })
        }
    }*/


}

