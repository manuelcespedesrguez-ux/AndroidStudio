package com.example.b16reciclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PaisAdapter(
    private val listaPaises: MutableList<String>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<PaisAdapter.PaisViewHolder>() {

    class PaisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textPais)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pais, parent, false)
        return PaisViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaisViewHolder, position: Int) {
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