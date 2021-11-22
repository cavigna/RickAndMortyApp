package com.example.rickandmorty.model.api


import com.google.gson.annotations.SerializedName

data class Locacion(
    @SerializedName("created")
    var created: String = "",
    @SerializedName("dimension")
    var dimension: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("residents")
    var residents: List<String> = listOf(),
    @SerializedName("type")
    var type: String = "",
    @SerializedName("url")
    var url: String = ""
)