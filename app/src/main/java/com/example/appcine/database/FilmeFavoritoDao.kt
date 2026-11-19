package com.example.appcine.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmeFavoritoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(filme: FilmeFavorito)

    @Query("SELECT * FROM filmes_favoritos")
    suspend fun listarTodos(): List<FilmeFavorito>
}