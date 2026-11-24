package com.example.b20cardview

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/*
    TODO

    -¿PARA QUE SIRVE Y POR QUE SE CREA? -> Controla la pantalla principal y une todas las piezas anteriores. Se crea como punto de arranque visual e interaccion con el usuario

    -PARTES CLAVE -> El metodo onCreate, la configuracion del RecyclerView( asignandole un LinearLayout y el AnimeAdapter) y el evento setOnClickListener del boton

    -EJEMPLO SENCILLO -> Es el jefe de la obra que reune los materiales (DataRepository), al trabajador(AnimeAdapter) y el espacio fisico (RecyclerView) para que todo funcione.
*/

class MainActivity : AppCompatActivity() {
    private var recycler: RecyclerView? = null // ? Variable global para controlar el RecyclerView
    private var adapter: AnimeAdapter? = null // ? Variable global para guardar el adaptador
    private var lManager: RecyclerView.LayoutManager? = null // ? Varaible global para definir como se organizan las tarjetas
    private var repositorio: DataRepository? = null // ? Variable global para acceder al almacen de datos


    override fun onCreate(savedInstanceState: Bundle?) { // ? Primer metodo del ciclo de vida que se ejecuta al abrir la pantalla

        super.onCreate(savedInstanceState) // ? Ejecuta la inicializacion interna obligatoria de Android

        this.enableEdgeToEdge()// ? Extiende el diseño de la app a pantalla completa (por detras de las barras del sistema)

        setContentView(R.layout.activity_main) // ?  Carga la interfaz visual definida

        // ? Añade margenes internos dinamicos al contenedor princial para que la barra de notificaciones o los botones del telefono no tapen la app
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recycler = findViewById(R.id.recyclerView) // ? Localiza el RecyclerView en el diseño XML y lo guarda en la variable

        recycler?.setHasFixedSize(true) // ? Mejora el rendimiento indicando que el contenedor de la lista mantendra un tamaño fijo en pantalla

        lManager = LinearLayoutManager(this) // ? Configura una distribucion lineal vertical(una tarjeta debajo de la otra)

        recycler?.layoutManager = lManager // ? Asigna esa distribucion vertical al RecyclerView

        repositorio = DataRepository() // ? Crea el repositorio, ejecutando su init y cargando los 5 animes iniciales

        val animeAdapter = AnimeAdapter(repositorio!!.obtenerListaAnimes()) // ? Crea el adaptador entregandole la lista del repositorio ("!!" fuerza a Kotlin a tratarla como no nula)

        adapter = animeAdapter // ? Guarda la referencia del adaptador en al variable global.

        recycler?.adapter = adapter // ? Conecta el adaptador con el RecyclerView para que empiece a pintar las tarjetas

        // !_________________________________________________________________________________________________________________________
        // ! METODO PARA AGREGAR ELEMENTOS A LA LISTA

        val btnAgregar = findViewById<Button>(R.id.button) // ? Localiza el boton en el XML

        btnAgregar.setOnClickListener { // ? Escucha el evento de clic sobre el boton

            // ? Lista con los animes disponibles para elegir uno al azar
            val listaBase = listOf(
                Anime(R.drawable.angel, "Angel Beats", 230),
                Anime(R.drawable.death, "Death Note", 456),
                Anime(R.drawable.fate, "Fate Stay Night", 342),
                Anime(R.drawable.nhk, "Welcome to the NHK", 645),
                Anime(R.drawable.suzumiya, "Suzumiya Haruhi", 459)
            )

            val nuevoAnime = listaBase.random() // ? Elige un anime aleatorio de la lista

            animeAdapter.addAnime(nuevoAnime) // ? Añade ese anime al adaptador y actualiza la lista

            recycler?.scrollToPosition(animeAdapter.itemCount - 1) // ? Desplaza el scroll hasta el final
        }
    } // <-- Llave de cierre de onCreate
} // <-- Llave de cierre de MainActivity