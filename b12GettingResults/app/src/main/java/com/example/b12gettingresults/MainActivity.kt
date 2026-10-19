package com.example.b12gettingresults

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.sql.Date
import java.sql.Time

class MainActivity : AppCompatActivity() {

    private lateinit var fecha: EditText
    private lateinit var tiempo: EditText
    private lateinit var resultado: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        )

        fecha = findViewById(R.id.fecha)
        tiempo = findViewById(R.id.tiempo)
        resultado = findViewById(R.id.resultado)
        button = findViewById(R.id.button)

        fecha.setText(java.time.LocalDate.now().toString())
        tiempo.setText(java.time.LocalTime.now().toString())

    }
}