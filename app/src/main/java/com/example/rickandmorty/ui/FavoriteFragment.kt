package com.example.rickandmorty.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.application.RickApp
import com.example.rickandmorty.databinding.FragmentFavoriteBinding
import com.example.rickandmorty.listadapter.FavListAdapter
import com.example.rickandmorty.model.db.PersonajeFavorito
import com.example.rickandmorty.viewmodel.RickModelFactory
import com.example.rickandmorty.viewmodel.RickViewModel


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var application: Application

    private val viewModel by viewModels<RickViewModel> {
        RickModelFactory((application as RickApp).repositorio)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application = requireActivity().application

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        val recyclerView = binding.recyclerView
        val adapter = FavListAdapter(object : FavListAdapter.EliminarPersonajeFav{
            override fun eliminar(personaje: PersonajeFavorito) {
                viewModel.eliminarFavorito(personaje)
                Toast.makeText(requireContext(), "Personaje Elminado", Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.listadoFavorito.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return binding.root
    }


}