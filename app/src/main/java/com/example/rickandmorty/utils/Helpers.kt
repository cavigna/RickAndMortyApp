package com.example.rickandmorty.utils

import com.example.rickandmorty.model.api.Resultado
import com.example.rickandmorty.model.db.Personaje

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