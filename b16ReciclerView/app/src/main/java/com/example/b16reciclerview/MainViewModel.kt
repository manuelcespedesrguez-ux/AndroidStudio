package com.example.b16reciclerview // Cambia por tu paquete si es necesario

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val countriesRepository = Countries()

    // Estado reactivo privado con la lista de objetos Pais
    private val _paises = MutableStateFlow<List<Pais>>(emptyList())
    // Estado público de solo lectura para la vista
    val paises: StateFlow<List<Pais>> = _paises.asStateFlow()

    init {
        // Carga inicial de la lista predeterminada de países
        _paises.value = countriesRepository.nameList
    }

    fun eliminarPais(posicion: Int) {
        val listaActual = _paises.value.toMutableList()
        if (posicion in listaActual.indices) {
            listaActual.removeAt(posicion)
            _paises.value = listaActual // Emisión de la nueva lista a la UI
        }
    }

    // Intenta buscar el país en el repositorio y añadirlo si existe y no está repetido
    fun agregarPais(textoBusqueda: String): ResultadoAgregar {
        val paisEncontrado = countriesRepository.buscar(textoBusqueda)

        return when {
            paisEncontrado == null -> {
                ResultadoAgregar.NoEncontrado(textoBusqueda)
            }
            _paises.value.any { it.nombre == paisEncontrado.nombre } -> {
                ResultadoAgregar.YaExiste(paisEncontrado.nombre)
            }
            else -> {
                val listaActual = _paises.value.toMutableList()
                listaActual.add(paisEncontrado)
                _paises.value = listaActual
                ResultadoAgregar.Exito(paisEncontrado)
            }
        }
    }
}

// Sealed class auxiliar para comunicar los resultados de negocio a la Activity de forma limpia
sealed class ResultadoAgregar {
    data class Exito(val pais: Pais) : ResultadoAgregar()
    data class NoEncontrado(val query: String) : ResultadoAgregar()
    data class YaExiste(val nombrePais: String) : ResultadoAgregar()
}