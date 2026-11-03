package com.example.b14calendarpicker

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val calendarView = findViewById<CalendarView>(R.id.calendarView);
        val texto3 = findViewById<EditText>(R.id.texto3);
        val texto2 = findViewById<EditText>(R.id.texto2);
        val button = findViewById<Button>(R.id.button2);

        var fechaSeleccionada: Long = calendarView.date;

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            texto2.setText(formatearFecha(calendar.timeInMillis))

        }

        button.setOnClickListener {
            val textoIngresado = texto3.text.toString()
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            try {
                val fecha = formato.parse(textoIngresado)
                if (fecha != null) {
                    calendarView.date = fecha.time
                }
            } catch (e: Exception) {
                println("Tas equivocado " + e.printStackTrace() )
            }
        }
    }

    private fun formatearFecha(milisegundos: Long): String {
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formato.format(milisegundos)
    }
}