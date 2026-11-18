package com.example.b15timepicker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los componentes de la interfaz
        val timePicker = findViewById<TimePicker>(R.id.timePicker)
        val btnGetTime = findViewById<Button>(R.id.button)
        val etInputTime = findViewById<EditText>(R.id.editTextTime)
        val tvSelectedTime = findViewById<TextView>(R.id.getTime)

        // Configurar el TimePicker en formato de 24 horas por defecto
        timePicker.setIs24HourView(true)

        // Acción al hacer clic en el botón
        btnGetTime.setOnClickListener {
            val inputtext = etInputTime.text.toString()

            // Comprobar si el usuario ha escrito algo con el formato HH:mm
            if (inputtext.contains(":")) {
                val parts = inputtext.split(":")
                if (parts.size == 2) {
                    val hour = parts[0].toIntOrNull()
                    val minute = parts[1].toIntOrNull()

                    if (hour != null && minute != null && hour in 0..23 && minute in 0..59) {
                        // 1. Actualizar el TimePicker con la hora escrita
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            timePicker.hour = hour
                            timePicker.minute = minute
                        } else {
                            @Suppress("DEPRECATION")
                            timePicker.currentHour = hour
                            @Suppress("DEPRECATION")
                            timePicker.currentMinute = minute
                        }

                        // 2. Mostrar la hora en el TextView debajo del reloj
                        val timeString = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
                        tvSelectedTime.text = timeString

                        Toast.makeText(this, "Hora aplicada correctamente", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }
            }

            val hour: Int
            val minute: Int

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                hour = timePicker.hour
                minute = timePicker.minute
            } else {
                @Suppress("DEPRECATION")
                hour = timePicker.currentHour
                @Suppress("DEPRECATION")
                minute = timePicker.currentMinute
            }

            val timeString = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
            tvSelectedTime.text = timeString
            Toast.makeText(this, "Hora seleccionada: $timeString", Toast.LENGTH_SHORT).show()
        }
    }
}