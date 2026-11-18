package com.example.b19basketspinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class AdaptadorEquipo(
    context: Context,
    layoutResId: Int,
    private val listaEquipos: MutableList<Equipo>
) : ArrayAdapter<Equipo>(context, layoutResId, listaEquipos) {

    // número de equipos que hay en el ListView
    override fun getCount(): Int = listaEquipos.size

    // devuelve el Equipo de la posición indicada
    override fun getItem(position: Int): Equipo = listaEquipos[position]

    override fun getItemId(position: Int): Long = position.toLong()

    // infla equipo.xml y lo rellena con los datos del Equipo (bind dato-vista)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
            ?: LayoutInflater.from(context).inflate(R.layout.equipo, parent, false)

        val clubName = view.findViewById<TextView>(R.id.tv_clubName)
        val country = view.findViewById<TextView>(R.id.tv_country)
        val city = view.findViewById<TextView>(R.id.tv_city)
        val year = view.findViewById<TextView>(R.id.tv_year)
        val imagen = view.findViewById<ImageView>(R.id.iv_escudo)

        val elemento = listaEquipos[position]
        clubName.text = elemento.nombre
        country.text = elemento.pais
        city.text = elemento.ciudad
        year.text = elemento.fundacion.toString()
        imagen.setImageResource(elemento.idImage)

        // al pulsar un equipo se borra de la lista
        view.setOnClickListener {
            listaEquipos.remove(elemento)
            notifyDataSetChanged()
        }

        return view
    }

    fun addEquipo(equipo: Equipo) {
        listaEquipos.add(equipo)
        notifyDataSetChanged()
    }
}
