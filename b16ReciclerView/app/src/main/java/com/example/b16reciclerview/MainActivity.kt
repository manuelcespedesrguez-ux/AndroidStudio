package com.example.b16ReciclerView

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.b16reciclerview.Countries
import com.example.b16reciclerview.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var button: Button

    private lateinit var adaptador: Adaptador
    private lateinit var paises: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // IDs actualizados según activity_main.xml
        recyclerView = findViewById(R.id.recyclerView)
        editText = findViewById(R.id.editTextText)
        button = findViewById(R.id.button)

        paises = Countries().nameList

        adaptador = Adaptador(paises) { position ->
            paises.removeAt(position)
            adaptador.notifyItemRemoved(position)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adaptador

        button.setOnClickListener {
            val nuevoPais = editText.text.toString().trim()

            if (nuevoPais.isNotEmpty()) {
                paises.add(nuevoPais)
                adaptador.notifyItemInserted(paises.size - 1)
                editText.text.clear()
                recyclerView.smoothScrollToPosition(paises.size - 1)
            }
        }
    }
}