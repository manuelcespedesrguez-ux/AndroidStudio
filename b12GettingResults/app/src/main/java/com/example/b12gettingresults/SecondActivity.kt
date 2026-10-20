package com.example.b12gettingresults

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private lateinit var fecha: TextView;
private lateinit var tiempo: TextView;
private lateinit var resultado: TextView;
private lateinit var boton: Button;

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fecha=findViewById<TextView>(R.id.FechaSecond);
        tiempo=findViewById<TextView>(R.id.HoraSecond);
        resultado=findViewById<TextView>(R.id.ResultadoSecond);
        boton=findViewById<Button>(R.id.BotonSecond);

        val cadena= "HOY ES: "+intent.getStringExtra("fecha")+ "--"+ intent.getStringExtra("tiempo")
        fecha.text=cadena;


        fecha.text=intent.getStringExtra("fecha")
        tiempo.text=intent.getStringExtra("tiempo")

        resultado.text=cadena

        boton.setOnClickListener{
            val intentResultado = Intent().apply {
                putExtra("resultadoSecond", cadena)
            }

            setResult(RESULT_OK, intentResultado)
            finish()
        }
    }
}