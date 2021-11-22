package com.example.rickandmorty.repository

import com.example.rickandmorty.db.RickDao
import com.example.rickandmorty.model.NetworkResult
import com.example.rickandmorty.model.Resultado
import com.example.rickandmorty.model.db.Personaje
import com.example.rickandmorty.network.ApiService

class Repositorio(private val api: ApiService, private val dao: RickDao) {


    suspend fun listadoPersonajesTodosApi(pagina:Int ) = api.todosLosPersonajes(page = pagina)
    suspend fun listadoPersonajesApi() = api.todosLosPersonajes()


    suspend fun personajeRandomApi() = api.personajeAPIRandom((1..826).random())
    fun personajeRandomDB() = dao.personajeRandomDB((1..826).random())


    suspend fun agregarListadoPersonaDB(listadoPersonaje: List<Personaje>) = dao.agregarListadoPersonajes(listadoPersonaje)
    //suspend fun agregarTodosPersonaDB(listadoPersonaje: List<Personaje>) = dao.agregarListadoPersonajes(listadoPersonaje)

    fun listadoPersonajeDB() = dao.listarPersonajesDB()



    suspend fun personajeRandomSealed() : NetworkResult<Resultado>{
        val respuesta = api.personajeAPIRandomSealed((1..826).random())

        return if (respuesta.data?.isSuccessful == true){
            NetworkResult.Success(respuesta.data.body())
        }else{
            NetworkResult.Error(respuesta.message.toString())
        }
    }
}