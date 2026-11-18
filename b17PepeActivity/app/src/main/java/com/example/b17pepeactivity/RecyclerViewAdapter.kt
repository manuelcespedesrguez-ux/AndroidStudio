package com.example.b17pepeactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val listaNombres: List<Country>) :
    RecyclerView.Adapter<RecyclerViewAdapter.NombresViewHolder>() {

    // Creamos la clase NombresViewHolder que extiende de RecyclerView.ViewHolder
    class NombresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Obtenemos las referencias a los controles del layout elemento.xml
        val imgFlag: ImageView = itemView.findViewById(R.id.imgFlag)
        val tvNombre: TextView = itemView.findViewById(R.id.Texto)
        val tvCapital: TextView = itemView.findViewById(R.id.Capital)
    }

    // Creamos el ViewHolder e inflamos el layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NombresViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.elemento, parent, false)
        return NombresViewHolder(view)
    }

    // Conectamos el ViewHolder con el modelo (los datos de cada país)
    override fun onBindViewHolder(holder: NombresViewHolder, position: Int) {
        val country = listaNombres[position]

        // Asignamos los valores del objeto Country a las vistas de la celda
        holder.tvNombre.text = country.nombre // Ajusta según las propiedades de tu clase Country
        holder.tvCapital.text = country.capital
        // holder.imgFlag.setImageResource(country.flagResId) // Descomenta si usas recursos locales para la bandera
    }

    // Devolvemos el tamaño total de la lista
    override fun getItemCount(): Int {
        return listaNombres.size
    }
}