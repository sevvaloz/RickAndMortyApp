package com.example.rickandmortyapp.utils

interface CharacterRowClickListener<T> {
    fun onCharacterRowClick(pos: Int, item: T)
}