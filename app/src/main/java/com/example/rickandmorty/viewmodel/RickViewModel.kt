package com.example.rickandmorty.viewmodel

import androidx.lifecycle.*
import com.example.rickandmorty.model.Resultado
import com.example.rickandmorty.model.db.Personaje
import com.example.rickandmorty.repository.Repositorio
import com.example.rickandmorty.utils.mapearAPItoDB
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RickViewModel(private val repositorio: Repositorio) : ViewModel() {


    var listadoPersonajeApi = MutableLiveData<List<Resultado>>()

    //var listadoPersonajeApi = mutableListOf<Resultado>()
    val listadoPersonajesDB = repositorio.listadoPersonajeDB().asLiveData()
    private var _personajes = MutableLiveData<List<Personaje>>()

    init {
        //listarPersonajesAPI()
        agregarListadoDB()
        buscarpersonajeRandom()
        agregarTodosPersonajesDB()

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

    fun agregarTodosPersonajesDB() {
        viewModelScope.launch(IO) {
            withContext(Main) {
                for (i in 1..42) {
                    val listadoApi = repositorio.listadoPersonajesTodosApi(pagina = i).resultados
                    repositorio.agregarListadoPersonaDB(mapearAPItoDB(listadoApi))
                }
            }
        }
    }



    var personajeRandomApi = MutableLiveData<Resultado>()
    fun buscarpersonajeRandom() {
        viewModelScope.launch(IO) {
            val personaje = repositorio.personajeRandomApi()
            personajeRandomApi.postValue(personaje)

        }
    }

    val personajeRandomDB = repositorio.personajeRandomDB()



}


class RickModelFactory(private val repositorio: Repositorio) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RickViewModel(repositorio) as T
    }
}
/*
    val personajeRandomSealed = MutableLiveData<NetworkResult<Resultado>>(NetworkResult.Loading())
    fun prueba() {
        viewModelScope.launch(IO) {

            personajeRandomSealed.postValue(repositorio.personajeRandomSealed())
        }
    }
 */

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
