package com.example.rickandmorty.network

import com.example.rickandmorty.model.NetworkResult
import com.example.rickandmorty.model.Resultado
import com.example.rickandmorty.model.RickInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character/")
    suspend fun todosLosPersonajes(
        @Query("page") page:Int = 1
    ): RickInfoResponse


    @GET("character/{id}")
    suspend fun personajeAPIRandom(
        @Path("id") id:Int
    ): Resultado

    @GET("character/{id}")
    suspend fun personajeAPIRandomSealed(
        @Path("id") id:Int
    ): NetworkResult<Response<Resultado>>
}


/*



    @GET("character/")
    suspend fun todosLosPersonajes(
        @Query("page") page:Int = 1
    ): NetworkResult<Response<RickInfoResponse>>
 */