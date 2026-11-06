package com.example.b17pepeactivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //Creas las variables que vamos a usar
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var button: Button

    private lateinit var buttonEliminar: Button
    private lateinit var adapter: RecyclerViewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //instanciamos las variables
        recyclerView = findViewById(R.id.ReciclerView)
        button = findViewById(R.id.Boton)
        buttonEliminar = findViewById(R.id.BotonEliminar)
        editText = findViewById(R.id.Nombres)
        //creamos el adapter y le pasamos la lista de nombres
        adapter = RecyclerViewAdapter(Country.listaNombres)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //Con el primer boton añadimos un nuevo nombre a la lista del adapter
        button.setOnClickListener {
            //recogemos el texto del editText y lo convertimos a string
            val nombre = editText.text.toString().trim()
            //Comprobamos que no este vacio antes de añadirlo
            if (nombre.isNotEmpty()) {
                //Extraemos el codigo ISO de dos caracteres a partir del texto y creamos un nuevo objeto Nombres
                val codigoIso = if (nombre.length >= 2) nombre.take(2).lowercase() else "es"
                val capital = nombre.substringAfter(",").trim().take(20)
                val nuevoObjeto = Country(nombre, codigoIso, capital)
                //Lo añadimos a la lista
                Country.listaNombres.add(nuevoObjeto)
                //Notificamos al adapter que se ha añadido un nuevo elemento
                adapter.notifyItemInserted(Country.listaNombres.size - 1)
                //Le añadimos un autoscroll al nuevo elemento poniendolo en ultima posicion de la lista
                recyclerView.scrollToPosition(Country.listaNombres.size - 1)
                //Limpiamos el editText
                editText.text.clear()
            }
        }
        //Con el segundo boton eliminaremos un nombre que escojamos
        buttonEliminar.setOnClickListener {
            //Recogemos el texto del editText y lo convertimos a string
            val nombre = editText.text.toString().trim()
            //comprobamos que ni la lista ni el nombre esten vacios
            if (Country.listaNombres.isNotEmpty() && nombre.isNotEmpty()) {
                //recogemos el indice del elemento que vamos a eliminar
                val indice = Country.listaNombres.indexOfFirst { it.nombre == nombre }
                //Comprobamos que sea un indice valido
                if (indice != -1) {
                    //Eliminamos de la lista mediante el indice
                    Country.listaNombres.removeAt(indice)
                    //Notificamos al adapter del cambio
                    adapter.notifyItemRemoved(indice)
                    //Limpiamos el editText
                    editText.text.clear()
                }
            }
        }
    }
}