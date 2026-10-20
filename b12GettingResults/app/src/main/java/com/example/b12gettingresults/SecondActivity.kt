package com.example.b12gettingresults

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main2),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        )

        val editTextDate = findViewById<TextView>(R.id.editTextDate)
        val editTextTime = findViewById<TextView>(R.id.editTextTime)
        val button2 = findViewById<Button>(R.id.button2)
        val resultado = findViewById<TextView>(R.id.resultado)

        val formateadorFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        editTextDate.text = LocalDate.now().format(formateadorFecha)
        val formateadorHora = DateTimeFormatter.ofPattern("HH:mm")
        editTextTime.text = LocalTime.now().format(formateadorHora)

        button2.setOnClickListener {
            val cadenaFusionada = "${editTextDate.text} - ${editTextTime.text}"

            val intent = Intent()
            intent.putExtra("cadena", cadenaFusionada)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}