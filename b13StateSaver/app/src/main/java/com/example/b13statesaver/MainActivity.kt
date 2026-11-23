package com.example.b13statesaver

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    companion object{
        const val KEY_COUNTER = "KEY_COUNTER"
        const val TEXT_COUNTER = "TEXT_COUNTER"
    }
    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var textView: TextView

    private lateinit var texto: String
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editText=findViewById<EditText>(R.id.editText)
        textView=findViewById<TextView>(R.id.TextViewCounter)
        button=findViewById<Button>(R.id.Button)
        button.setOnClickListener {
            counter++
            textView.text = counter.toString()
        }

        texto=editText.text.toString()
    }
    //metodo para guardar el estado de la aplicacion
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show()
        outState.putInt(KEY_COUNTER, counter)
        outState.putString(TEXT_COUNTER, editText.text.toString())
    }

    //metodo para recuperar el estado de la aplicacion
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counter = savedInstanceState.getInt(KEY_COUNTER)
        texto = savedInstanceState.getString(TEXT_COUNTER, "")
        textView.text = counter.toString()
        editText.setText(texto.uppercase())
    }
}