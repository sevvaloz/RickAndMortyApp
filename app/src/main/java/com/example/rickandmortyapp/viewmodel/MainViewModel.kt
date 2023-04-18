package com.example.rickandmortyapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.models.models.character.CharacterList
import com.example.rickandmortyapp.models.models.location.LocationList
import com.example.rickandmortyapp.repository.Repository
import com.example.rickandmortyapp.repository.RepositoryImpl
import com.example.rickandmortyapp.utils.State
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(): ViewModel() {


    private val characters = MutableLiveData<CharacterList>()
    val charactersData: LiveData<CharacterList> get() = characters

    private val locations = MutableLiveData<LocationList>()
    val locationsData: LiveData<LocationList> get() = locations

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
                    }
                }

                override fun onFailure(call: Call<LocationList?>, t: Throwable) {
                    Log.d("TAG", "ServiceFailed. Message1: ${t.message}")
                }
            })
        }
    }

    fun getCharacters(_ids: Array<Int>){
        viewModelScope.launch {
            repository.getCharacter(ids = _ids).enqueue(object : Callback<CharacterList> {
                override fun onResponse(call: Call<CharacterList>, response: Response<CharacterList>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.d("TAG", "ServiceSuccess2")
                            characters.postValue(response.body())
                        }?: kotlin.run {
                            Log.d("TAG", "EmptyBody2")
                        }
                    } else {
                        Log.d("TAG", "ServiceFailed. ResponseMessage2: ${response.message()}")
                        Log.d("ServiceFailedCode", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<CharacterList>, t: Throwable) {
                    Log.d("TAG", "ServiceFailed. Message2: ${t.message}")
                }
            })
        }
    }



/*    fun getCharacter(){
        viewModelScope.launch {
            location_repository.getLocation(1).enqueue(object : Callback<LocationList?> {
                override fun onResponse(call: Call<LocationList?>, response: Response<LocationList?>) {
                    if(response.isSuccessful){
                        response.body()?.results?.forEach {
                            it.residents.forEach{residentUrl ->
                                val regex = "/character/(\\d+)".toRegex()
                                val matchResult = regex.find(residentUrl)
                                val characterId = matchResult?.groupValues?.get(1)

                                if(characterId != null){
                                    character_repository.getCharacter(characterId.toInt()).enqueue(object : Callback<CharacterList?> {
                                        override fun onResponse(call: Call<CharacterList?>, response: Response<CharacterList?>) {
                                            if(response.isSuccessful){
                                                response.body()?.let {
                                                    characters.postValue(it)
                                                }
                                            } else {
                                                Log.d("TAG2","failed char details")
                                            }
                                        }

                                        override fun onFailure(call: Call<CharacterList?>, t: Throwable) {
                                            Log.d("TAG2","failed char details: ${t.message}")
                                        }
                                    })
                                }
                            }
                        }
                    } else {
                        Log.d("TAG2","failed location details")
                    }
                }

                override fun onFailure(call: Call<LocationList?>, t: Throwable) {
                    Log.d("TAG2","failed location details: ${t.message}")
                }
            })
        }
    }*/

}

