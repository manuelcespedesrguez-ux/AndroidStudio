package com.example.b20cardview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimeAdapter(private val items: List<Anime>) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {
    class AnimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.imagen)
        val nombre: TextView = view.findViewById(R.id.nombre)
        val visitas: TextView = view.findViewById(R.id.visitas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_card, parent, false)
        return AnimeViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = items[position]
        holder.imagen.setImageResource(anime.imagen)
        holder.nombre.text = anime.nombre
        holder.visitas.text = "Visitas: ${anime.visitas}"
    }
}
