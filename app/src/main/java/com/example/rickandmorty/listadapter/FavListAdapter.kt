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
import com.example.rickandmorty.model.db.PersonajeFavorito

class FavListAdapter(private val eliminarPersonajeFav: EliminarPersonajeFav): ListAdapter<PersonajeFavorito,FavViewHolder>(FavComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val personaje = getItem(position)
          holder.unidorTarjeta(personaje)

        eliminarPersonajeFav.eliminar(personaje)

    }

    interface EliminarPersonajeFav{

        fun eliminar(personaje: PersonajeFavorito)

    }

}

class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val binding = ItemRowBinding.bind(itemView)

    companion object{
        fun create(parent: ViewGroup): FavViewHolder{
            val layoutInflaterB = LayoutInflater.from(parent.context)
            val binding = ItemRowBinding.inflate(layoutInflaterB, parent, false)
            return FavViewHolder(binding.root)
        }
    }
     fun unidorTarjeta(personaje: PersonajeFavorito) {
        with(binding) {
            when (personaje.status) {
                "Alive" -> imageViewCircle.setImageResource(R.drawable.green_dot)
            }
            textViewNombreHome.text = personaje.name
            textViewStatusHome.text = personaje.status
            textViewLocationHome.text = personaje.locationName
            textViewSpeciesHome.text = "Especie: ${personaje.species}"
            imageViewPersonajeDelDia.load(personaje.image)
        }
    }

}

class FavComparator:DiffUtil.ItemCallback<PersonajeFavorito>() {
    override fun areItemsTheSame(oldItem: PersonajeFavorito, newItem: PersonajeFavorito): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: PersonajeFavorito,
        newItem: PersonajeFavorito
    ): Boolean {
        return oldItem.id == newItem.id
    }

}
