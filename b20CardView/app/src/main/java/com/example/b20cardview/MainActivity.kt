package com.example.b20cardview

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var recycler: RecyclerView? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var lManager: RecyclerView.LayoutManager? = null
    private var repositorio: DataRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar RecyclerView
        recycler = findViewById(R.id.recyclerView)
        recycler?.setHasFixedSize(true)

        lManager = LinearLayoutManager(this)
        recycler?.layoutManager = lManager

        repositorio = DataRepository()
        adapter = AnimeAdapter(repositorio!!.obtenerListaAnimes())
        recycler?.adapter = adapter

        // Configurar el botón AGREGAR
        val btnAgregar = findViewById<Button>(R.id.button)
        btnAgregar.setOnClickListener {
            val nuevoAnime = Anime(R.drawable.angel, "Nuevo Anime", 100)
            repositorio?.agregarAnime(nuevoAnime)

            val nuevaPosicion = repositorio!!.obtenerListaAnimes().size - 1
            adapter?.notifyItemInserted(nuevaPosicion)
            recycler?.scrollToPosition(nuevaPosicion)
        }
    }
}