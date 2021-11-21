package com.example.rickandmorty.viewmodel

import androidx.lifecycle.*
import com.example.rickandmorty.model.api.Resultado
import com.example.rickandmorty.model.db.Personaje
import com.example.rickandmorty.repository.Repositorio
import com.example.rickandmorty.utils.mapearAPItoDB
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RickViewModel(private val repositorio: Repositorio) : ViewModel() {


    var listadoPersonajeApi = MutableLiveData<List<Resultado>>()

    //var listadoPersonajeApi = mutableListOf<Resultado>()
    val listadoPersonajesDB = repositorio.listadoPersonajeDB().asLiveData()
    private var _personajes = MutableLiveData<List<Personaje>>()

    init {
        listarPersonajesAPI()
        agregarListadoDB()
        buscarpersonajeRandom()
    }

    fun listarPersonajesAPI() {
        viewModelScope.launch(IO) {
            listadoPersonajeApi.postValue(repositorio.listadoPersonajesApi().resultados)

        }
    }


    fun agregarListadoDB() {

        viewModelScope.launch(IO) {
            val listadoApi = repositorio.listadoPersonajesApi().resultados
            repositorio.agregarListadoPersonaDB(mapearAPItoDB(listadoApi))
        }

    }

    var personajeRandom = MutableLiveData<Resultado>()


    fun buscarpersonajeRandom(){
        viewModelScope.launch(IO) {
            val personaje =repositorio.personajeRandomApi()
            personajeRandom.postValue(personaje)
        }
    }
}


class RickModelFactory(private val repositorio: Repositorio) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RickViewModel(repositorio) as T
    }
}


/*
    fun listaPersonajesAPI() {

        viewModelScope.launch(IO) {
            try {
                val response = repositorio.listadoPersonajesApi()
                _personajes.postValue(NetworkResult.Success(response.data))
            } catch (ioe: IOException) {
                _personajes.postValue(NetworkResult.Error("Error"))
            }

        }
    }
 */
