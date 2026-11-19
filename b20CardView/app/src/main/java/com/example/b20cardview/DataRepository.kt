package com.example.b20cardview

class DataRepository {
    var items: MutableList<Anime?>

    init {
        items = ArrayList<Anime?>()
        items.add(Anime(R.drawable.angel, "Angel Beats", 230))
        items.add(Anime(R.drawable.death, "Death Note", 456))
        items.add(Anime(R.drawable.fate, "Fate Stay Night", 342))
        items.add(Anime(R.drawable.nhk, "Welcome to the NHK", 645))
        items.add(Anime(R.drawable.suzumiya, "Suzumiya Haruhi", 459))
    }

    fun obtenerListaAnimes(): MutableList<Anime?> {
        return items
    }

    fun agregarAnime(anime: Anime) {
        items.add(anime)
    }
}