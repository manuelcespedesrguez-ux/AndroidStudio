package com.example.b12gettingresults

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var fecha: EditText;
    private lateinit var tiempo: EditText;
    private lateinit var boton: Button;
    private lateinit var resultado: TextView;

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Callback que se ejecuta cuando SecondActivity responde
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val cadena = data?.getStringExtra("resultadoSecond")
            resultado.text = cadena
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fecha = findViewById<EditText>(R.id.fecha);
        tiempo = findViewById<EditText>(R.id.Tiempo);
        boton = findViewById<Button>(R.id.Enviar);
        resultado = findViewById<TextView>(R.id.Resultado);

        //cargar fecha y hora actual en los editText
        val formateadorFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        fecha.setText(java.time.LocalDate.now().format(formateadorFecha));
        val formateadorTiempo = DateTimeFormatter.ofPattern("HH:mm:ss")
        tiempo.setText(java.time.LocalTime.now().format(formateadorTiempo));


        boton.setOnClickListener {
            val miIntent = Intent(this, SecondActivity::class.java).apply {
                putExtra("fecha", fecha.text.toString())
                putExtra("tiempo", tiempo.text.toString())
            }
            resultLauncher.launch(miIntent)
        }
    }

    /** override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == 56) {
    if (resultCode == RESULT_OK) {
    val cadena = data?.getStringExtra("resultadoSecond")
    resultado.text = cadena
    }
    }
    }*/

}