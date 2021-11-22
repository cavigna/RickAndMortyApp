package com.example.rickandmorty.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.application.RickApp
import com.example.rickandmorty.databinding.FragmentHomeBinding
import com.example.rickandmorty.model.db.Personaje
import com.example.rickandmorty.utils.convertirAFav
import com.example.rickandmorty.viewmodel.RickModelFactory
import com.example.rickandmorty.viewmodel.RickViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
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
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


//        viewModel.personajeRandomApi.observe(viewLifecycleOwner, { personaje->
//            with(binding) {
//
//                when(personaje.status){
//                    "Alive" -> imageViewCircle.setImageResource(R.drawable.green_dot)
//                }
//                textViewNombreHome.text = personaje.name
//                textViewStatusHome.text = personaje.status
//                textViewLocationHome.text = personaje.location.name
//                textViewSpeciesHome.text = personaje.species
//                imageViewPersonajeDelDia.load(personaje.image)
//            }
//        })

        viewModel.listadoPersonajesDB.observe(viewLifecycleOwner, {

        })

        viewModel.personajeRandomDB.observe(viewLifecycleOwner, { personaje->

            unidorTarjeta(personaje)

            binding.cardView.setOnLongClickListener {
                viewModel.agregarFavorito(convertirAFav(personaje))
                Toast.makeText(requireContext(), "Personaje Guardado", Toast.LENGTH_SHORT).show()
                true
            }


        })

        binding.imageViewRefresh.setOnClickListener {
            viewModel.funPerRandomDB().observe(viewLifecycleOwner, {
                unidorTarjeta(personaje = it)
            })

        }






        return binding.root
    }

    private fun unidorTarjeta(personaje: Personaje) {
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

/*
        viewModel.personajeRandom.observe(viewLifecycleOwner, {personaje->
            with(binding) {

                when(personaje.status){
                    "Alive" -> imageViewCircle.setImageResource(R.drawable.green_dot)
                }
                textViewNombreHome.text = personaje.name
                textViewStatusHome.text = personaje.status
                textViewLocationHome.text = personaje.location.name
                textViewSpeciesHome.text = personaje.species
                imageViewPersonajeDelDia.load(personaje.image)
            }
        })
 */

/*
        viewModel.listadoPersonajesDB.observe(viewLifecycleOwner,{
            val personaje = it[0]
            unidorTarjeta(personaje)
        })
 */

/*
        viewModel.personajeRandom.observe(viewLifecycleOwner, {personaje->
            with(binding) {
                textViewNombreHome.text = personaje.name
                textViewGenderHome.text = personaje.gender
                textViewSpeciesHome.text = personaje.status
                imageViewPersonajeDelDia.load(personaje.image)
            }
        })
 */