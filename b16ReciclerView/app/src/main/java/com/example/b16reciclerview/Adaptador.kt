package com.example.b16ReciclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.b16reciclerview.R

class Adaptador(
    private val listaPaises: MutableList<String>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<Adaptador.PaisesVista>() {

    class PaisesVista(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ID actualizado según item_pais.xml
        val textView: TextView = itemView.findViewById(R.id.textPais)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisesVista {
        // Layout inflado actualizado a item_pais
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_pais, parent, false)
        return PaisesVista(vista)
    }

    override fun onBindViewHolder(holder: PaisesVista, position: Int) {
        holder.textView.text = listaPaises[position]

        holder.itemView.setOnClickListener {
            val pos = holder.bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                onItemClick(pos)
            }
        }
    }

    override fun getItemCount(): Int = listaPaises.size
}