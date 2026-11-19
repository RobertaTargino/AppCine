package com.example.appcine.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "filmes_favoritos")
data class FilmeFavorito(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "titulo")
    val titulo: String,

    @ColumnInfo(name = "ano")
    val ano: String,

    @ColumnInfo(name = "genero")
    val genero: String,

    @ColumnInfo(name = "sinopse")
    val sinopse: String,

    @ColumnInfo(name = "avaliacao")
    val avaliacao: String
)