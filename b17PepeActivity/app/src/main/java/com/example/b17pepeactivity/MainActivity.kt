package com.example.b17pepeactivity



import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Instanciación del ViewModel mediante KTX
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adaptador: MiAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializamos el adaptador pasando la acción a realizar al pulsar (eliminar)
        adaptador = MiAdaptador { posicion ->
            viewModel.eliminarPais(posicion)
        }
        recyclerView.adapter = adaptador

        // Observamos el estado expuesto por el ViewModel
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.paises.collect { listaActualizada ->
                    adaptador.actualizarLista(listaActualizada)
                }
            }
        }
    }
}