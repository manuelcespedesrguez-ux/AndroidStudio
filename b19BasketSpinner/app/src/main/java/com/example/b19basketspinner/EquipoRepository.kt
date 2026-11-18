package com.example.b19basketspinner

class EquipoRepository {

    val listaEquipos: ArrayList<Equipo> = poblarSpinner()

    companion object {
        fun poblarSpinner(): ArrayList<Equipo> {
            val lista = ArrayList<Equipo>()
            lista.add(Equipo("Maccabi Basketball Club", "Tel Aviv", "Israel", 1932, R.drawable.club2))
            lista.add(Equipo("Košarkaški Klub Crvena Zvezda", "Belgrado", "Serbia", 1945, R.drawable.club1))
            lista.add(Equipo("Basketball Club Žalgiris", "Kaunas", "Lituania", 1944, R.drawable.club3))
            lista.add(Equipo("Club Deportivo Baskonia", "Vitoria", "España", 1959, R.drawable.club4))
            lista.add(Equipo("Panathinaikos Basketball Club", "Atenas", "Grecia", 1919, R.drawable.club5))
            return lista
        }
    }
}
