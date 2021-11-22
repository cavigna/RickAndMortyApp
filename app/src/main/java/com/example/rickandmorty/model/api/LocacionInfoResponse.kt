package com.example.rickandmorty.model.api


import com.example.rickandmorty.model.Info
import com.google.gson.annotations.SerializedName

data class LocacionInfoResponse(
    @SerializedName("info")
    var info: Info = Info(),
    @SerializedName("results")
    var locacions: List<Locacion> = listOf()
)