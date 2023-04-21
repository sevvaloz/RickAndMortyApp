package com.example.rickandmortyapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.databinding.ActivityCharacterDetailsBinding
import com.example.rickandmortyapp.utils.findCharacterEpisodeNumber

class CharacterDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailsBinding
    val episodeNumbers = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        //hide application action bar
        supportActionBar?.hide()

        //fun calls
        viewBinding()
        doBack()
        setDetails()
    }

    private fun viewBinding() {
        binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun doBack(){
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setDetails() {
        binding.name.text = intent.getStringExtra("name")
        binding.created.text = intent.getStringExtra("created")
        binding.gender.text  = intent.getStringExtra("gender")
        binding.status.text  = intent.getStringExtra("status")
        binding.specy.text  = intent.getStringExtra("species")
        binding.location.text  = intent.getStringExtra("locationName")
        binding.origin.text  = intent.getStringExtra("originName")
        Glide.with(this).load(intent.getStringExtra("image")).into(binding.image)
        val episodeUrls = intent.getStringArrayListExtra("episodes")
        episodeUrls?.forEach { episodeUrl ->
            episodeNumbers.add(episodeUrl.findCharacterEpisodeNumber())
        }
        binding.episodes.text = episodeNumbers.joinToString()
    }
}








