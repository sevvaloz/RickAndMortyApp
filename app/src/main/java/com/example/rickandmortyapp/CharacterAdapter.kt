package com.example.rickandmortyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.databinding.ItemCharacterBinding
import com.example.rickandmortyapp.CharacterData.Character

class CharacterAdapter(private val characterList: List<Character>): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    class CharacterViewHolder(val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.binding.apply {
            val character = characterList[position]
            characterName.text = character.name
            Glide.with(holder.binding.characterImage).load(character.image).into(characterImage)
        }
    }


}