package com.example.rickandmortyapp.utils

fun String.findCharacterId(): Int {
    return (this.substringAfterLast(delimiter = "/")).toInt()
}

fun String.findCharacterEpisodeNumber(): String {
    return (this.substringAfterLast(delimiter = "/"))
}

fun String.findCreatedDate(): String {
    return (this.substringBefore(delimiter = "T"))
}

fun String.findCharacterTime(): String {
    return this.substringAfter(delimiter = "T").substringBefore(delimiter = ".")
}