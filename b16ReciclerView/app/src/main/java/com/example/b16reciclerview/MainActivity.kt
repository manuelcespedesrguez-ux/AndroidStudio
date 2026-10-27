package com.example.b16reciclerview

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val editText = findViewById<EditText>(R.id.editTextText)
        val button = findViewById<Button>(R.id.button)

        val listaPaises = mutableListOf(
            "España",
            "Francia",
            "Italia",
            "Portugal",
            "Bosnia y Echarsegomina",
            "Argentina",
            "México",
            "Japón",
            "Brasil"
        )

        lateinit var adapter: PaisAdapter

        adapter = PaisAdapter(listaPaises) { position ->
            listaPaises.removeAt(position)
            adapter.notifyItemRemoved(position)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        button.setOnClickListener {
            val nuevoPais = editText.text.toString().trim()

            if (nuevoPais.isNotEmpty()) {
                listaPaises.add(nuevoPais)
                adapter.notifyItemInserted(listaPaises.size - 1)

                editText.text.clear()
                recyclerView.scrollToPosition(listaPaises.size - 1)
            }
        }
    }
}