package com.example.b16reciclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador(
    private var listaPaises: List<Pais> = emptyList(),
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<Adaptador.PaisesVista>() {

    class PaisesVista(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textPais)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisesVista {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_pais, parent, false)
        return PaisesVista(vista)
    }

    override fun onBindViewHolder(holder: PaisesVista, position: Int) {
        val pais = listaPaises[position]
        holder.textView.text = "${pais.bandera} ${pais.nombre} - ${pais.capital}"

        holder.itemView.setOnClickListener {
            val pos = holder.bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                onItemClick(pos)
            }
        }
    }

    override fun getItemCount(): Int = listaPaises.size

    fun actualizarLista(nuevaLista: List<Pais>) {
        this.listaPaises = nuevaLista
        notifyDataSetChanged()
    }
}