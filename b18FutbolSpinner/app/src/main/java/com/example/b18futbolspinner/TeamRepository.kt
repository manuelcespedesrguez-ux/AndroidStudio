package com.example.b18futbolspinner

object TeamRepository {

    fun getTeams(): List<Team> = listOf(
        Team("FC Barcelona", "Barcelona", "España", 1899, R.drawable.image6),
        Team("Real Madrid", "Madrid", "España", 1902, R.drawable.image7),
        Team("Atlético de Madrid", "Madrid", "España", 1903, R.drawable.image8),
        Team("Sevilla FC", "Sevilla", "España", 1890, R.drawable.image9),
        Team("Real Betis", "Sevilla", "España", 1907, R.drawable.image10),
        Team("Coristanco SD", "Coristanco", "España", 1970, R.drawable.image11)
    )
}