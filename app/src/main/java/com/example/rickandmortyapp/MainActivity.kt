package com.example.rickandmortyapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hide application action bar
        supportActionBar?.hide()

        //fun calls
        viewBinding()
        init()
        sendRequest()
        observeLocationData()
        observeCharacterData()
    }

    private fun viewBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        viewModel.setRepo(RepositoryImpl())
    }

    private fun sendRequest(){
        viewModel.getLocations()
    }

    private fun changeUI(){
        binding.infoTxt?.visibility = View.GONE
        binding.characterRecyclerview.visibility = View.VISIBLE
    }

    private fun observeLocationData(){
        viewModel.locationsData.observe(this){ locList ->
            loc_recyclerview = binding.locationRecyclerview
            locationAdapter = LocationAdapter(locList.results.toTypedArray(), object : LocationRowClickListener<Location>{
                override fun onLocationRowClick(pos: Int, item: Location) {
                    Log.d("LocationName", item.name)
                    val characterIdList = item.residents.map { url -> url.findCharacterId() }
                    viewModel.getCharacters(characterIdList)
                    changeUI()
                }
            }, binding.locationRecyclerview )
            loc_recyclerview.adapter = locationAdapter
            loc_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
        }
    }
    private fun observeCharacterData(){
        viewModel.charactersData.observe(this){ charList ->
            char_recyclerview = binding.characterRecyclerview
            characterAdapter = CharacterAdapter(charList, object : CharacterRowClickListener<Character>{
                override fun onCharacterRowClick(pos: Int, item: Character) {
                    sendCharacterData(item)
                }
            })
            char_recyclerview.adapter = characterAdapter
            char_recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun sendCharacterData(item: Character){
        val _intent = Intent(this@MainActivity, CharacterDetailsActivity::class.java)
        _intent.putExtra("name", item.name)
        _intent.putExtra("created", item.created)
        _intent.putExtra("gender", item.gender)
        _intent.putExtra("status", item.status)
        _intent.putExtra("species", item.species)
        _intent.putExtra("locationName", item.location.name)
        _intent.putExtra("originName", item.origin.name)
        _intent.putExtra("image", item.image)
        _intent.putStringArrayListExtra("episodes", item.episode)
        startActivity(_intent)
    }



}