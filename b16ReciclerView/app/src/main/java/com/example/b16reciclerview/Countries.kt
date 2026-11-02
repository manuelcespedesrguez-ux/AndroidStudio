package com.example.b16reciclerview // Cambia por tu paquete si es necesario

import java.text.Normalizer

// Modelo de datos de un país
data class Pais(
    val nombre: String,
    val capital: String,
    val bandera: String
)

// Repositorio de países
class Countries {

    // Catálogo completo en el que se busca
    private val catalogo: List<Pais> = listOf(
        Pais("España", "Madrid", "🇪🇸"),
        Pais("Francia", "París", "🇫🇷"),
        Pais("Italia", "Roma", "🇮🇹"),
        Pais("Alemania", "Berlín", "🇩🇪"),
        Pais("Portugal", "Lisboa", "🇵🇹"),
        Pais("Reino Unido", "Londres", "🇬🇧"),
        Pais("Irlanda", "Dublín", "🇮🇪"),
        Pais("Países Bajos", "Ámsterdam", "🇳🇱"),
        Pais("Bélgica", "Bruselas", "🇧🇪"),
        Pais("Suiza", "Berna", "🇨🇭"),
        Pais("Austria", "Viena", "🇦🇹"),
        Pais("Grecia", "Atenas", "🇬🇷"),
        Pais("Suecia", "Estocolmo", "🇸🇪"),
        Pais("Noruega", "Oslo", "🇳🇴"),
        Pais("Dinamarca", "Copenhague", "🇩🇰"),
        Pais("Finlandia", "Helsinki", "🇫🇮"),
        Pais("Polonia", "Varsovia", "🇵🇱"),
        Pais("Rusia", "Moscú", "🇷🇺"),
        Pais("Turquía", "Ankara", "🇹🇷"),
        Pais("Estados Unidos", "Washington D. C.", "🇺🇸"),
        Pais("Canadá", "Ottawa", "🇨🇦"),
        Pais("México", "Ciudad de México", "🇲🇽"),
        Pais("Cuba", "La Habana", "🇨🇺"),
        Pais("Colombia", "Bogotá", "🇨🇴"),
        Pais("Venezuela", "Caracas", "🇻🇪"),
        Pais("Perú", "Lima", "🇵🇪"),
        Pais("Chile", "Santiago", "🇨🇱"),
        Pais("Argentina", "Buenos Aires", "🇦🇷"),
        Pais("Brasil", "Brasilia", "🇧🇷"),
        Pais("Uruguay", "Montevideo", "🇺🇾"),
        Pais("Ecuador", "Quito", "🇪🇨"),
        Pais("Bolivia", "Sucre", "🇧🇴"),
        Pais("Paraguay", "Asunción", "🇵🇾"),
        Pais("China", "Pekín", "🇨🇳"),
        Pais("Japón", "Tokio", "🇯🇵"),
        Pais("Corea del Sur", "Seúl", "🇰🇷"),
        Pais("India", "Nueva Delhi", "🇮🇳"),
        Pais("Egipto", "El Cairo", "🇪🇬"),
        Pais("Marruecos", "Rabat", "🇲🇦"),
        Pais("Sudáfrica", "Pretoria", "🇿🇦"),
        Pais("Australia", "Canberra", "🇦🇺")
    )

    // Lista inicial que se muestra al abrir la app
    val nameList: List<Pais> = listOf("España", "Francia", "Italia", "Alemania", "Portugal")
        .mapNotNull { buscar(it) }

    // Busca un país por nombre ignorando mayúsculas, tildes y espacios sobrantes
    fun buscar(texto: String): Pais? {
        val consulta = normalizar(texto)
        if (consulta.isEmpty()) return null
        return catalogo.firstOrNull { normalizar(it.nombre) == consulta }
    }

    private fun normalizar(texto: String): String {
        return Normalizer.normalize(texto.trim(), Normalizer.Form.NFD)
            .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
            .lowercase()
    }
}