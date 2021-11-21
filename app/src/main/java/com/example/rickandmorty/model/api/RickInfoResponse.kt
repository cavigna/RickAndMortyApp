package com.example.rickandmorty.model.api


import com.google.gson.annotations.SerializedName

data class RickInfoResponse(
    @SerializedName("info")
    var info: Info = Info(),
    @SerializedName("resultados")
    var resultados: List<Resultado> = listOf()
)