package com.example.rickandmorty.utils

import com.example.rickandmorty.model.Resultado
import com.example.rickandmorty.model.db.Personaje
import com.example.rickandmorty.model.db.PersonajeFavorito

fun mapearAPItoDB(listadoApi: List<Resultado>): List<Personaje> {

    val listadoResultado = mutableListOf<Personaje>()
    listadoApi?.forEach {
        listadoResultado.add(
            Personaje(
                id = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                gender = it.gender,
                originName = it.origin.name,
                urlOrigin = it.origin.url,
                locationName = it.location.name,
                locationUrl = it.location.url,
                image = it.image,
                urlPersonaje = it.url
            )
        )
    }
    return listadoResultado

}

 fun convertirAFav(personaje: Personaje): PersonajeFavorito {
    return PersonajeFavorito(
        id = personaje.id,
        name = personaje.name,
        status = personaje.status,
        species = personaje.species,
        gender =  personaje.gender,
        originName = personaje.originName,
        urlOrigin = personaje.urlOrigin,
        locationUrl = personaje.locationUrl,
        locationName = personaje.locationName,
        image = personaje.image,
        urlPersonaje = personaje.urlPersonaje
    )
}