package com.example.appcine.api

import retrofit2.http.GET
import retrofit2.http.Query

interface FilmeApiService {

    @GET("/")
    suspend fun buscarFilmes(
        @Query("apikey") chave: String,
        @Query("s") pesquisa: String
    ): RespostaFilmes

    @GET("/")
    suspend fun buscarDetalhes(
        @Query("apikey") chave: String,
        @Query("i") id: String
    ): DetalhesFilme
}