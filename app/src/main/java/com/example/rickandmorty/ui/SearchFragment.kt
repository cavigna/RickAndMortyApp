package com.example.rickandmorty.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.application.RickApp
import com.example.rickandmorty.databinding.FragmentSearchBinding
import com.example.rickandmorty.listadapter.PersonajeListAdapter
import com.example.rickandmorty.model.db.Personaje
import com.example.rickandmorty.utils.convertirAFav
import com.example.rickandmorty.viewmodel.RickModelFactory
import com.example.rickandmorty.viewmodel.RickViewModel


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var application: Application
    private val viewModel by activityViewModels<RickViewModel> {
        RickModelFactory((application as RickApp).repositorio)
    }

    private lateinit var adapter: PersonajeListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application = requireActivity().application

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)


        val recyclerView = binding.recyclerSearch

        adapter = PersonajeListAdapter(object : PersonajeListAdapter.MiAgregador{
            override fun agregarPersonajeB(personaje: Personaje) {
            viewModel.agregarFavorito(convertirAFav(personaje))
            Toast.makeText(requireContext(), "Personaje Agregado", Toast.LENGTH_SHORT).show()
            }

        })
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.listadoPersonajesDB.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        val searchView = binding.searchView2

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!= null){
                    searchDB(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!= null){
                    searchDB(newText)
                }
                return true
            }

        })






        return binding.root
    }

    private fun searchDB(query:String){
        val searchQuery = "%$query%"

        viewModel.buscarPersonaje(searchQuery).observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

}