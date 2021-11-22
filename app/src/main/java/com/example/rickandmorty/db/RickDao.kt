package com.example.rickandmorty.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.model.db.Personaje
import com.example.rickandmorty.model.db.PersonajeFavorito
import kotlinx.coroutines.flow.Flow

@Dao
interface RickDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarPersonaje(personaje: Personaje)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarListadoPersonajes(listadoPersonaje: List<Personaje>)

    @Query("SELECT * FROM personajes_tabla")
    fun listarPersonajesDB(): Flow<List<Personaje>>

    @Query("SELECT * FROM personajes_tabla where id =:id")
    fun personajeRandomDB(id:Int): Flow<Personaje>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarPersonajeFavorito(personajeFavorito: PersonajeFavorito)

    @Query("SELECT * FROM personajes_tabla")
    fun listarPersonajesFavoritos(): Flow<List<PersonajeFavorito>>



}