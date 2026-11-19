package com.example.appcine.api

import com.google.gson.annotations.SerializedName

data class DetalhesFilme(
    @SerializedName("imdbID")
    val id: String,

    @SerializedName("Title")
    val titulo: String,

    @SerializedName("Year")
    val ano: String,

    @SerializedName("Genre")
    val genero: String,

    @SerializedName("Plot")
    val sinopse: String,

    @SerializedName("imdbRating")
    val avaliacao: String
)