package com.example.rickandmorty.db

import androidx.room.*
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

    @Query("SELECT * FROM personajes_tabla WHERE name LIKE '%' || :search || '%'")
    fun buscarPersonaje2(search: String?): Flow<List<Personaje>>

    @Query("SELECT * FROM personajes_tabla WHERE name LIKE :search")
    fun buscarPersonaje(search: String?): Flow<List<Personaje>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarPersonajeFavorito(personajeFavorito: PersonajeFavorito)

    @Delete
    suspend fun eliminarPersonajeFavorito(personaje: PersonajeFavorito)

    @Query("SELECT * FROM personajes_favoritos_tabla")
    fun listarPersonajesFavoritos(): Flow<List<PersonajeFavorito>>



}