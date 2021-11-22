package com.example.rickandmorty.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.application.RickApp
import com.example.rickandmorty.databinding.FragmentFavoriteBinding
import com.example.rickandmorty.databinding.FragmentPersonajesBinding
import com.example.rickandmorty.listadapter.PersonajeListAdapter
import com.example.rickandmorty.model.db.Personaje
import com.example.rickandmorty.utils.convertirAFav
import com.example.rickandmorty.viewmodel.RickModelFactory
import com.example.rickandmorty.viewmodel.RickViewModel


class PersonajesFragment : Fragment() {
    private lateinit var binding: FragmentPersonajesBinding

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
        binding = FragmentPersonajesBinding.inflate(layoutInflater, container, false)

        val recyclerView = binding.recyclerview
        val adapter = PersonajeListAdapter(object : PersonajeListAdapter.MiAgregador{
            override fun agregarPersonajeB(personaje: Personaje) {
                viewModel.agregarFavorito(convertirAFav(personaje))

            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        viewModel.listadoPersonajesDB.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return binding.root
    }


}