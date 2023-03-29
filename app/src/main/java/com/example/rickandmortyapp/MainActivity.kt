package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hide application action bar
        supportActionBar?.hide()


        //functions
        bind()
        observeViewModel()

    }

    private fun bind(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun observeViewModel(){
        viewModel.charactersData.observe(this){ characterList ->
            binding.characterRecyclerview.adapter = CharacterAdapter(characterList)
            binding.characterRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.locationsData.observe(this){ locationList ->
            binding.locationRecyclerview.adapter = LocationAdapter(locationList)
            binding.locationRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }


}