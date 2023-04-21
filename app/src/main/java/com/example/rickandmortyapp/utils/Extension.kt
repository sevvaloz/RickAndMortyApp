package com.example.rickandmortyapp.utils

fun String.findCharacterId(): Int {
    return (this.substring(this.lastIndexOf("/")+1)).toInt()
}

fun String.findCharacterEpisodeNumber(): String {
    return (this.substring(this.lastIndexOf("/")+1))
}