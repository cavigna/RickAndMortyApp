package com.example.rickandmorty.application

import android.app.Application
import com.example.rickandmorty.db.BaseDeDatos
import com.example.rickandmorty.network.ApiService
import com.example.rickandmorty.repository.Repositorio
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickApp: Application() {

    private val retrofitClient by lazy {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private val baseDeDatos by lazy { BaseDeDatos.getDataBase(this) }

     val repositorio by lazy { Repositorio(retrofitClient,baseDeDatos.dao()) }
}