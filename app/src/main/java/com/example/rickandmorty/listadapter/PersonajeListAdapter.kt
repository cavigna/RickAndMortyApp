package com.example.rickandmorty.listadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemRowBinding
import com.example.rickandmorty.model.db.Personaje


class PersonajeListAdapter(private val miAgregador: MiAgregador) :
    ListAdapter<Personaje, PersonajeViewHolder>(PersonajeComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
       return PersonajeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val personaje = getItem(position)

        holder.unidorTarjeta(personaje)
        holder.binding.cardView.setOnClickListener {
            miAgregador.agregarPersonajeB(personaje)
        }

    }

    interface MiAgregador {
        fun agregarPersonajeB(personaje: Personaje)
    }
}

class PersonajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemRowBinding.bind(itemView)

    companion object {
        fun create(parent: ViewGroup): PersonajeViewHolder {
            val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PersonajeViewHolder(binding.root)
        }
    }

    fun unidorTarjeta(personaje: Personaje) {
        with(binding) {
            when (personaje.status) {
                "Alive" -> imageViewCircle.setImageResource(R.drawable.green_dot)
                "Dead" -> imageViewCircle.setImageResource(R.drawable.red_dot)
            }
            textViewNombreHome.text = personaje.name
            textViewStatusHome.text = personaje.status
            textViewLocationHome.text = personaje.locationName
            textViewSpeciesHome.text = "Especie: ${personaje.species}"
            imageViewPersonajeDelDia.load(personaje.image)
        }
    }

}

class PersonajeComparator : DiffUtil.ItemCallback<Personaje>() {
    override fun areItemsTheSame(oldItem: Personaje, newItem: Personaje): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Personaje, newItem: Personaje): Boolean =
        oldItem.id == newItem.id

}
