package com.example.rickandmorty.listadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.databinding.ItemRowBinding
import com.example.rickandmorty.model.db.PersonajeFavorito

class FavListAdapter: ListAdapter<PersonajeFavorito,FavViewHolder>(FavComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val personaje = getItem(position)

        with(holder.binding){
            imageViewPersonajeDelDia.load(personaje.image)
        }
    }
}

class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val binding = ItemRowBinding.bind(itemView)

    companion object{
        fun create(parent: ViewGroup): FavViewHolder{
            val layoutInflaterB = LayoutInflater.from(parent.context)
            val binding = ItemRowBinding.inflate(layoutInflaterB, parent, false)

            return FavViewHolder(binding.root)
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
