package com.example.rickandmorty.repository

import com.example.rickandmorty.db.RickDao
import com.example.rickandmorty.model.db.Personaje
import com.example.rickandmorty.network.ApiService

class Repositorio(private val api: ApiService, private val dao: RickDao) {


    suspend fun listadoPersonajesApi() = api.todosLosPersonajes()

    suspend fun personajeRandomApi() = api.personajeAPIRandom((1..826).random())

    suspend fun agregarListadoPersonaDB(listadoPersonaje: List<Personaje>) = dao.agregarListadoPersonajes(listadoPersonaje)

    fun listadoPersonajeDB() = dao.listarPersonajesDB()

}