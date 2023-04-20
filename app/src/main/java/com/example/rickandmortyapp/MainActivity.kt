package com.example.rickandmortyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.models.models.location.Location
import com.example.rickandmortyapp.adapter.CharacterAdapter
import com.example.rickandmortyapp.adapter.LocationAdapter
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import com.example.rickandmortyapp.repository.RepositoryImpl
import com.example.rickandmortyapp.utils.CharacterRowClickListener
import com.example.rickandmortyapp.utils.LocationRowClickListener
import com.example.rickandmortyapp.utils.findCharacterId
import com.example.rickandmortyapp.viewmodel.MainViewModel
import com.example.rickandmortyapp.models.models.character.Character
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loc_recyclerview: RecyclerView
    private lateinit var char_recyclerview: RecyclerView
    private var locationAdapter: LocationAdapter? = null
    private var characterAdapter: CharacterAdapter? = null
    private val viewModel: MainViewModel by viewModels()
    private val jsonObject: JSONObject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(R.style.Theme_RickAndMortyApp)
        setContentView(R.layout.activity_main)

        //hide application action bar
        supportActionBar?.hide()

        //fun calls
        viewBinding()
        init()
        sendRequest()
        observeViewModel()


    }

    fun viewBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    private fun init(){
        viewModel.setRepo(RepositoryImpl())
    }

    private fun sendRequest(){
        viewModel.getLocations()
    }

    private fun observeViewModel(){
        viewModel.locationsData.observe(this){
            loc_recyclerview = binding.locationRecyclerview
            locationAdapter = LocationAdapter(it.results.toTypedArray(), object : LocationRowClickListener<Location>{

                override fun onLocationRowClick(pos: Int, item: Location) {
                    Log.d("LocationName", item.name)
                    val characterIdList = item.residents.map { url -> url.findCharacterId() }
                    viewModel.getCharacters(characterIdList)
                }
            } )
            loc_recyclerview.adapter = locationAdapter
            loc_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)

        }

        viewModel.charactersData.observe(this){ charList ->
            char_recyclerview = binding.characterRecyclerview
            characterAdapter = CharacterAdapter(charList, object : CharacterRowClickListener<Character>{
                override fun onCharacterRowClick(pos: Int, item: Character) {
                    Log.d("CharacterID", item.id.toString())
                    //viewModel.getSingleCharacter(item.id)
                    startActivity(Intent(this@MainActivity, CharacterDetailsActivity::class.java)).toString()
                }
            })

            char_recyclerview.adapter = characterAdapter
            char_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        }



    }



}