package com.example.b20cardview

class DataRepository { // * Declara la clase que actua como fuente de datos
    var items: MutableList<Anime?> // ? Declara la variable que contendra la lista MODIFICABLE de animes

    init { // ? Bloque constructor que se ejecuta automaticamente en cuanto se inicia "DataRepository"

        items = ArrayList<Anime?>() // ? Crea una lista vacia en memoria

        // ? Crea 5 instancias de "Anime" con su imagen, titulo y visita y las guarda dentro de la lista "items"
        items.add(Anime(R.drawable.angel, "Angel Beats", 230))
        items.add(Anime(R.drawable.death, "Death Note", 456))
        items.add(Anime(R.drawable.fate, "Fate Stay Night", 342))
        items.add(Anime(R.drawable.nhk, "Welcome to the NHK", 645))
        items.add(Anime(R.drawable.suzumiya, "Suzumiya Haruhi", 459))

        /*

        ! Ejemplo de acceso
            for(Anime anime: items) {
                System.out.println(anime.getNombre() + " - " + anime.getVisitas());
            }
    */
    }

    // ? Metodo publico que entrega la lista cargada a quien la pida
    fun obtenerListaAnimes(): MutableList<Anime?> {
        return items
    }
}