package com.example.rickandmorty.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmorty.model.db.Personaje
import com.example.rickandmorty.model.db.PersonajeFavorito


@Database(entities = [Personaje::class, PersonajeFavorito::class], version = 1, exportSchema = false)
abstract class BaseDeDatos : RoomDatabase() {
    abstract fun dao() : RickDao

    companion object {

        @Volatile
        private var INSTANCE: BaseDeDatos? = null

        fun getDataBase(context: Context): BaseDeDatos {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDeDatos::class.java,
                    "rick_and_morty_db"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}