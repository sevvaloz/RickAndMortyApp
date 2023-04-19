package com.example.rickandmortyapp.utils

interface LocationRowClickListener<T> {
    fun onLocationRowClick(pos: Int, item: T)
}