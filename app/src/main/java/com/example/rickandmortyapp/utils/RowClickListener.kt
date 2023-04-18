package com.example.rickandmortyapp.utils

interface RowClickListener<T> {
    fun onRowClick(pos: Int, item: T)
}