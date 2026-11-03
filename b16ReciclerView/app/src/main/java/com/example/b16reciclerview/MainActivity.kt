package com.example.b16reciclerview // Cambia por tu paquete si es necesario

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Instanciación del ViewModel mediante KTX
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adaptador: Adaptador

    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        editText = findViewById(R.id.editTextText)
        button = findViewById(R.id.button)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializamos el adaptador pasando la lambda para eliminar al hacer clic en un ítem
        adaptador = Adaptador { posicion ->
            viewModel.eliminarPais(posicion)
        }
        recyclerView.adapter = adaptador

        // Observamos los cambios en el flujo de datos del ViewModel de forma segura
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.paises.collect { listaActualizada ->
                    adaptador.actualizarLista(listaActualizada)
                }
            }
        }

        // Configuración del botón para añadir nuevos países
        button.setOnClickListener {
            val texto = editText.text.toString().trim()

            if (texto.isNotEmpty()) {
                when (val resultado = viewModel.agregarPais(texto)) {
                    is ResultadoAgregar.NoEncontrado -> {
                        Toast.makeText(this, "No tengo datos de \"${resultado.query}\"", Toast.LENGTH_SHORT).show()
                    }
                    is ResultadoAgregar.YaExiste -> {
                        Toast.makeText(this, "${resultado.nombrePais} ya está en la lista", Toast.LENGTH_SHORT).show()
                    }
                    is ResultadoAgregar.Exito -> {
                        editText.text.clear()
                        recyclerView.smoothScrollToPosition(viewModel.paises.value.size - 1)

                        // Muestra la información detallada del país recién añadido
                        Toast.makeText(
                            this,
                            "${resultado.pais.bandera} ${resultado.pais.nombre}\nCapital: ${resultado.pais.capital}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}