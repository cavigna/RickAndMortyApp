package com.example.rickandmorty.model


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("url")
    var url: String = ""
)