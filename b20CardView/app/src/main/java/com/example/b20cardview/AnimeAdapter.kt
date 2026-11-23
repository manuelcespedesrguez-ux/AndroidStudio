package com.example.b20cardview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.b20cardview.AnimeAdapter.AnimeViewHolder


//ojo
/**
 * Creado por Hermosa Programación
 */
class AnimeAdapter(private val items: MutableList<Anime?>) :
    RecyclerView.Adapter<AnimeViewHolder?>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AnimeViewHolder {
        // Creamos una nueva vista inflando el layout anime_card.xml
        val v = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.anime_card, viewGroup, false)
        return AnimeViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: AnimeViewHolder, i: Int) {
        //ojo setImageResource
        viewHolder.imagen.setImageResource(items.get(i)!!.imagen)
        viewHolder.nombre.setText(items.get(i)!!.nombre)
        viewHolder.visitas.setText("Visitas: " + items.get(i)!!.visitas.toString())
    }

    //CLASE INTERNA ESTATICA PARA MANIPULAR LAS CARDS
    class AnimeViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // Campos respectivos de un item
        var imagen: ImageView
        var nombre: TextView
        var visitas: TextView


        init {
            imagen = v.findViewById<View?>(R.id.imagen) as ImageView
            nombre = v.findViewById<View?>(R.id.nombre) as TextView
            visitas = v.findViewById<View?>(R.id.visitas) as TextView
        }
    }
}
