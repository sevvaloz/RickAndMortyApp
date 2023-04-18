package com.example.rickandmortyapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ItemCharacterBinding
import com.example.rickandmortyapp.models.models.character.Result

class CharacterAdapter(private val characterList: List<Result>
): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    class CharacterViewHolder(val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = characterList.size

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.binding.apply {
            val character = characterList[position]
            characterName.text = character.name
            Glide.with(holder.binding.root).load(character.image).into(characterImage)

            if(character.gender == "Male"){
                Glide.with(holder.binding.root).load(R.drawable.male).into(characterGenderImage)
            }
            if(character.gender == "Female"){
                Glide.with(holder.binding.root).load(R.drawable.female).into(characterGenderImage)
            }
            if(character.gender == "Genderless"){
                Glide.with(holder.binding.root).load(R.drawable.genderless).into(characterGenderImage)
            }
            if(character.gender == "unknown"){
                Glide.with(holder.binding.root).load(R.drawable.unknown).into(characterGenderImage)
            }
        }
    }

}
