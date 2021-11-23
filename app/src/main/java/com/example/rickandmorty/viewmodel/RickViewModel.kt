package com.example.rickandmorty.viewmodel

import androidx.lifecycle.*
import com.example.rickandmorty.model.Resultado
import com.example.rickandmorty.model.db.Personaje
import com.example.rickandmorty.model.db.PersonajeFavorito
import com.example.rickandmorty.repository.Repositorio
import com.example.rickandmorty.utils.convertirAFav
import com.example.rickandmorty.utils.mapearAPItoDB
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RickViewModel(private val repositorio: Repositorio) : ViewModel() {


    val listadoPersonajesDB = repositorio.listadoPersonajeDB().asLiveData()
    val listadoFavorito = repositorio.listarFavoritos().asLiveData()



    init {
        agregarListadoDB()
        agregarTodosPersonajesDB()

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


    val personajeRandomDB = repositorio.personajeRandomDB().asLiveData()

    fun funPerRandomDB() = repositorio.personajeRandomDB(id = (1..826).random()).asLiveData()


    fun agregarFavorito(personajeFavorito: PersonajeFavorito) {
        viewModelScope.launch {
            repositorio.agregarFavorito(personajeFavorito)
        }
    }

    fun eliminarFavorito(personaje: PersonajeFavorito){
        viewModelScope.launch(IO){
            repositorio.eliminarFavorito(personaje)
        }
    }
    fun buscarPersonaje(query:String) = repositorio.buscarPersonaje(query).asLiveData()

}


class RickModelFactory(private val repositorio: Repositorio) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RickViewModel(repositorio) as T
    }
}

/*
Si lo implementara con Retro fit
    var personajeRandomApi = MutableLiveData<Resultado>()
    fun buscarpersonajeRandom() {
        viewModelScope.launch(IO) {
            val personaje = repositorio.personajeRandomApi()
            personajeRandomApi.postValue(personaje)

        }
    }
 */
