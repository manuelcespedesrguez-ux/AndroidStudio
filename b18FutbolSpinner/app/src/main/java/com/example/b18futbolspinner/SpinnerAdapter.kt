package com.example.b18futbolspinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class SpinnerAdapter(
    context: Context,
    private val teams: List<Team>
) : ArrayAdapter<Team>(context, 0, teams) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.elemento, parent, false
        )

        val escudo = view.findViewById<ImageView>(R.id.escudo)
        val name = view.findViewById<TextView>(R.id.name)
        val year = view.findViewById<TextView>(R.id.year)
        val country = view.findViewById<TextView>(R.id.country)
        val city = view.findViewById<TextView>(R.id.city)

        getItem(position)?.let {
            escudo.setImageResource(it.logoResId)
            name.text = it.name
            year.text = it.year.toString()
            country.text = it.country
            city.text = it.city
        }

        return view
    }
}