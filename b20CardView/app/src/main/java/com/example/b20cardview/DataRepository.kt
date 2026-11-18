package com.example.b20cardview

class DataRepository {
    fun obtenerListaAnimes(): MutableList<Anime> = mutableListOf(
        Anime(R.drawable.angel, "Angel Beats", 230),
        Anime(R.drawable.death, "Death Note", 456),
        Anime(R.drawable.fate, "Fate Stay Night", 342),
        Anime(R.drawable.nhk, "Welcome to the NHK", 645),
        Anime(R.drawable.suzumiya, "Suzumiya Haruhi", 459)
    )
}
