package com.example.rickandmorty.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "personajes_favoritos_tabla" )
data class PersonajeFavorito(
    @PrimaryKey
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val gender : String = "",
    val originName: String = "",
    val urlOrigin : String = "",
    val locationName: String = "",
    val locationUrl: String = "",
    val image: String = "",
    val urlPersonaje: String = ""




    )
