package com.example.rickandmorty.model


import com.google.gson.annotations.SerializedName

data class RickInfoResponse(
    @SerializedName("info")
    var info: Info = Info(),
    @SerializedName("results")
    var resultados: List<Resultado> = listOf()
)