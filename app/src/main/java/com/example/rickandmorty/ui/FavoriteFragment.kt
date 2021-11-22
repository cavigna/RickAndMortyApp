package com.example.rickandmorty.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.rickandmorty.R
import com.example.rickandmorty.application.RickApp
import com.example.rickandmorty.databinding.FragmentFavoriteBinding
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


        return binding.root
    }


}