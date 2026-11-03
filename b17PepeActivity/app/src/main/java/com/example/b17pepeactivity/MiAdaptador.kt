package com.example.b17pepeactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MiAdaptador(
    private var nameList: List<String> = emptyList(),
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<MiAdaptador.MiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.elemento, parent, false)
        return MiViewHolder(view)
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        holder.textView.text = nameList[position]

        // Notificamos el clic hacia arriba (MainActivity / ViewModel)
        holder.itemView.setOnClickListener {
            val currentPos = holder.bindingAdapterPosition
            if (currentPos != RecyclerView.NO_POSITION) {
                onItemClick(currentPos)
            }
        }
    }

    override fun getItemCount(): Int = nameList.size

    // Método para actualizar los datos desde el observador de la MainActivity
    fun actualizarLista(nuevaLista: List<String>) {
        this.nameList = nuevaLista
        notifyDataSetChanged()
    }

    inner class MiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.texto)
    }
}