package com.example.rickandmortyapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyapp.databinding.ActivityCharacterDetailsBinding
import com.example.rickandmortyapp.models.models.character.Character
import com.example.rickandmortyapp.viewmodel.MainViewModel

class CharacterDetailsActivity : AppCompatActivity() {

    private lateinit var currentCharacter: Character
    private lateinit var binding: ActivityCharacterDetailsBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        viewBinding()
        observeViewModel()
    }

    private fun viewBinding(){
        binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun observeViewModel(){

        viewModel.singleCharacterData.observe(this){


        }
    }


}