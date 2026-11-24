package com.example.b20cardview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*
    TODO

    -¿PARA QUE SIRVE Y POR QUE SE CREA? -> Conecta los datos (Anime) con el diseño visual de cada tarjeta(anime_card.xml) dentro del RecyclerView. Se crea porque Android recicla las tarjetas que salen de la pantalla al hacer scroll para ahorrar memoria

    -PARTES CLAVE ->
        AnimeViewHolder: Guarda en caché las referencias visuales(ImageView, TextView) de una tarjeta para no buscarla constantemente
        onCreateViewHolder: Crea el diseño vacio de una tarjeta nueva.
        onBindViewHolder: Rellena una tarjeta con los datos reales de un anime.
        getItemCount: Indica cuantos elementos hay en total.
        addAnime: Inserta un elemento nuevo en tiempo real y refresca la vista.

     -EJEMPLO SENCILLO -> Es como un camarero: recoge platos vacios en la cocina(onCreateViewHolder) y les sirve comida(onBindVieHolder) y los lleva a la mesa.

 */

class AnimeAdapter(private val items: MutableList<Anime?>) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() { // * Define al adaptador, recibe la lista modificable de animes y hereda el comportamiento base de RecyclerView.Adapter

    // ? Devuelve el tamaño total de la lista para que el RecyclerView sepa cuantas tarjetas debe gestionar
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AnimeViewHolder { // ? Se ejecuta automaticamente cuando hace falta crear una tarjeta visual nueva

        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.anime_card, viewGroup, false) // ? Infla el archivo XML "anime_card" en una vista manipulable en Kotlin sin anclarla aun al padre(false)

        return AnimeViewHolder(v) // ? Envuelve la vista recien creada dentro de un AnimeViewHolder y la devuelve
    }

    override fun onBindViewHolder(viewHolder: AnimeViewHolder, i: Int) { // ? Rellena una tarjeta existente con los datos del anime ubicados en la posicion "i"

        val item = items[i] // ? Extra el objeto "Anime" de la posicion actual [i]

        if (item != null) { // ? Verifica que el objeto exista(no sea nulo) antes de leer sus atributos

            // ? Si tiene un ID de imagen valido (distinto de 0) carga esa imagen en la tarjeta.
            if (item.imagen != 0) {
                viewHolder.imagen.setImageResource(item.imagen)
            }
            // ? Si el ID es 0, limpia el espacio de la imagen para que no se quede dibujada en una imagen reciclada anterior.
            else {
                viewHolder.imagen.setImageDrawable(null)
            }

            viewHolder.nombre.text = item.nombre ?: "" // ? Escribe el nombre en el TextView si es nulo, escribre un texto vacio (operador Elvis "?:").

            viewHolder.visitas.text = if ((item.nombre.isNullOrEmpty()) && item.visitas == 0) "" else "Visitas: ${item.visitas}" // ? Si el anime esta vacio(sin nombre y con 0 visitas), deja el campo en blanco; si tiene datos muestra "Visitas: X"
        }
    }

    fun addAnime(anime: Anime) { // ? Metodo para insertar un nuevo anime a la lista

        items.add(anime) // ? Añade el nuevo objeto al final de la lista "items"

        notifyItemInserted(items.size - 1) // ? Avisa al RecyclerView de que hay un nuevo elemento en la ultima posicion parea que lo dibuje en pantalla.
    }

    fun borrarAnime(posicion: Int) {
        if (posicion in items.indices) { // ? Verifica que la posición siga siendo válida

            items.removeAt(posicion) // ? Elimina el elemento de la lista en memoria

            notifyItemRemoved(posicion) // ? Avisa al RecyclerView para animar el borrado de esa tarjeta
        }
    }

    inner class AnimeViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imagen: ImageView = v.findViewById(R.id.imagen)
        val nombre: TextView = v.findViewById(R.id.nombre)
        val visitas: TextView = v.findViewById(R.id.visitas)

        init {
            // ? Detecta la pulsación larga sobre toda la tarjeta (v)
            v.setOnLongClickListener {
                val posicion = bindingAdapterPosition // ? Obtiene la posición real de la tarjeta pulsada

                if (posicion != RecyclerView.NO_POSITION) { // ? Evita errores si se pulsa mientras se anima un borrado

                    borrarAnime(posicion) // ? Llama a tu método para eliminarla

                }

                true // ? Indica que el evento ya se ha gestionado
            }
        }
    }
}