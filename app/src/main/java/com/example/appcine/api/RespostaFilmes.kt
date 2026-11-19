package com.example.appcine.api

import com.google.gson.annotations.SerializedName

data class RespostaFilmes(
    @SerializedName("Search")
    val filmes: List<Filme>?,

    @SerializedName("Response")
    val resposta: String
)

data class Filme(
    @SerializedName("Title")
    val titulo: String,

    @SerializedName("Year")
    val ano: String,

    @SerializedName("imdbID")
    val id: String
)