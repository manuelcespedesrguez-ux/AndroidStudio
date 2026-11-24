package com.example.b20cardview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var recycler: RecyclerView? = null
    private var adapter: AnimeAdapter? = null
    private var lManager: RecyclerView.LayoutManager? = null
    private var repositorio: DataRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recycler = findViewById(R.id.recyclerView)
        recycler?.setHasFixedSize(true)
        lManager = LinearLayoutManager(this)
        recycler?.layoutManager = lManager

        repositorio = DataRepository()

        // Referencia al nuevo TextView del activity_main.xml
        val tvSeleccionado = findViewById<TextView>(R.id.tvAnimeSeleccionado)

        // Inicialización del adaptador con la acción del clic corto
        val animeAdapter = AnimeAdapter(repositorio!!.obtenerListaAnimes()) { anime ->
            tvSeleccionado.text = anime.nombre
        }

        adapter = animeAdapter
        recycler?.adapter = adapter

        // Botón para agregar un anime aleatorio con datos reales
        val btnAgregar = findViewById<Button>(R.id.button)

        btnAgregar.setOnClickListener {
            val listaBase = listOf(
                Anime(R.drawable.angel, "Angel Beats", 230),
                Anime(R.drawable.death, "Death Note", 456),
                Anime(R.drawable.fate, "Fate Stay Night", 342),
                Anime(R.drawable.nhk, "Welcome to the NHK", 645),
                Anime(R.drawable.suzumiya, "Suzumiya Haruhi", 459)
            )

            val nuevoAnime = listaBase.random()
            animeAdapter.addAnime(nuevoAnime)
            recycler?.scrollToPosition(animeAdapter.itemCount - 1)
        }
    }
}