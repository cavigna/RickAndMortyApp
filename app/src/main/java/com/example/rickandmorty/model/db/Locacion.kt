package com.example.rickandmorty.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locacion_tabla")
data class Locacion(

    @PrimaryKey val id:Int = 0,
    val name: String = "",
    val type: String = "",
    val dimension: String = "",

)

/*
id	int	The id of the location.
name	string	The name of the location.
type	string	The type of the location.
dimension	string	The dimension in which the location is located.
residents	array (urls)	List of character who have been last seen in the location.
url	string (url)	Link to the location's own endpoint
 */