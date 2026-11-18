package com.example.b18futbolspinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val textViewTeam = findViewById<TextView>(R.id.team)
        val imageViewTeam = findViewById<ImageView>(R.id.imageView)

        val teams = TeamRepository.getTeams()

        val adapter = SpinnerAdapter(this, teams)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedTeam = teams[position]

                textViewTeam.text = selectedTeam.name
                imageViewTeam.setImageResource(selectedTeam.logoResId)

                Toast.makeText(
                    this@MainActivity,
                    "Seleccionado: ${selectedTeam.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Sin acción
            }
        }
    }
}