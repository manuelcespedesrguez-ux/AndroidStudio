package com.example.b20cardview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimeAdapter(
    private val items: MutableList<Anime?>,
    private val onItemClick: (Anime) -> Unit
) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AnimeViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.anime_card, viewGroup, false)
        return AnimeViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: AnimeViewHolder, i: Int) {
        val item = items[i]

        if (item != null) {
            if (item.imagen != 0) {
                viewHolder.imagen.setImageResource(item.imagen)
            } else {
                viewHolder.imagen.setImageDrawable(null)
            }

            viewHolder.nombre.text = item.nombre ?: ""
            viewHolder.visitas.text =
                if ((item.nombre.isNullOrEmpty()) && item.visitas == 0) "" else "Visitas: ${item.visitas}"
        }
    }

    fun addAnime(anime: Anime) {
        items.add(anime)
        notifyItemInserted(items.size - 1)
    }

    fun borrarAnime(posicion: Int) {
        if (posicion in items.indices) {
            items.removeAt(posicion)
            notifyItemRemoved(posicion)
        }
    }

    inner class AnimeViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imagen: ImageView = v.findViewById(R.id.imagen)
        val nombre: TextView = v.findViewById(R.id.nombre)
        val visitas: TextView = v.findViewById(R.id.visitas)

        init {
            // Clic corto: envía el anime pulsado a MainActivity
            v.setOnClickListener {
                val posicion = bindingAdapterPosition
                if (posicion != RecyclerView.NO_POSITION) {
                    val animePulsado = items[posicion]
                    if (animePulsado != null) {
                        onItemClick(animePulsado)
                    }
                }
            }

            // Clic largo: borra la tarjeta
            v.setOnLongClickListener {
                val posicion = bindingAdapterPosition
                if (posicion != RecyclerView.NO_POSITION) {
                    borrarAnime(posicion)
                }
                true
            }
        }
    }
}