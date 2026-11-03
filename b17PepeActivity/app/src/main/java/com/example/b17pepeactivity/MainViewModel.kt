package com.example.b17pepeactivity

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val countryRepository = Country()

    // Estado reactivo privado (mutable)
    private val _paises = MutableStateFlow<List<String>>(emptyList())
    // Estado público de solo lectura para la vista
    val paises: StateFlow<List<String>> = _paises.asStateFlow()

    init {
        // Carga inicial
        _paises.value = countryRepository.obtenerListaInicial()
    }

    fun eliminarPais(posicion: Int) {
        val listaActual = _paises.value.toMutableList()
        if (posicion in listaActual.indices) {
            listaActual.removeAt(posicion)
            _paises.value = listaActual // Emitimos una nueva lista para actualizar la UI
        }
    }

    fun agregarPais(nuevoPais: String) {
        if (nuevoPais.isNotBlank()) {
            val listaActual = _paises.value.toMutableList()
            listaActual.add(nuevoPais)
            _paises.value = listaActual
        }
    }
}