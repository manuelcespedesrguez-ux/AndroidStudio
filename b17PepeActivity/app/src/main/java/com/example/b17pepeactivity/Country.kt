package com.example.b17pepeactivity

class Country(val nombre: String, val countryCode: String, val capital: String){
    companion object {
        //Creamos una lista mutable de Nombres para poder añadir o eliminar elementos mas tarde
        val listaNombres: MutableList<Country> = mutableListOf(
            Country("España", "es", "Madrid"),
            Country("México", "mx", "Ciudad de México"),
            Country("Argentina", "ar", "Buenos Aires"),
            Country("Colombia", "co", "Bogotá"),
            Country("Chile", "cl", "Santiago"),
            Country("Perú", "pe", "Lima"),
            Country("Uruguay", "uy", "Montevideo"),
            Country("Venezuela", "ve", "Caracas"),
            Country("Ecuador", "ec", "Quito"),
            Country("Guatemala", "gt", "Ciudad de Guatemala"),
            Country("Costa Rica", "cr", "San José"),
            Country("Brasil", "br", "Brasilia"),
            Country("Panamá", "pa", "Ciudad de Panamá"),
            Country("Bolivia", "bo", "Sucre"),
            Country("Paraguay", "py", "Asunción"),
            Country("Francia", "fr", "París"),
            Country("Italia", "it", "Roma"),
            Country("Alemania", "de", "Berlín"),
            Country("Portugal", "pt", "Lisboa"),
            Country("Reino Unido", "gb", "Londres"),
            Country("Japón", "jp", "Tokio"),
            Country("Estados Unidos", "us", "Washington D.C.")
        )
    }
}