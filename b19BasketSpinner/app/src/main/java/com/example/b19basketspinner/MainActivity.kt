package com.example.b19basketspinner

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.EdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EdgeToEdge.enable(this)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listView = findViewById(R.id.listView)
        val equipoRepository = EquipoRepository()
        val adaptadorEquipo = AdaptadorEquipo(this, R.layout.equipo, equipoRepository.listaEquipos)
        listView.adapter = adaptadorEquipo

        val nombre = findViewById<EditText>(R.id.nombre)
        val ciudad = findViewById<EditText>(R.id.ciudad)
        val pais = findViewById<EditText>(R.id.pais)
        val fundacion = findViewById<EditText>(R.id.fundacion)

        button = findViewById(R.id.button)
        button.setOnClickListener {
            val sNombre = nombre.text.toString().trim()
            val sCiudad = ciudad.text.toString().trim()
            val sPais = pais.text.toString().trim()
            val sFundacion = fundacion.text.toString().trim()

            if (sNombre.isEmpty() || sCiudad.isEmpty() || sPais.isEmpty() || sFundacion.isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val anio = sFundacion.toIntOrNull()
            if (anio == null) {
                Toast.makeText(this, "El año no es válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // El ImageView no permite recuperar el id del drawable, se usa avatar_7 directamente
            adaptadorEquipo.addEquipo(Equipo(sNombre, sCiudad, sPais, anio, R.drawable.avatar_7))

            nombre.setText("")
            ciudad.setText("")
            pais.setText("")
            fundacion.setText("")
            nombre.requestFocus()
        }
    }
}
