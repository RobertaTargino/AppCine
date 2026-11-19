package com.example.appcine.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcine.api.Filme
import com.example.appcine.api.DetalhesFilme
import com.example.appcine.api.RetrofitInstance
import com.example.appcine.database.AppDatabase
import com.example.appcine.database.FilmeFavorito
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmeViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).filmeFavoritoDao()

    private val _filmes = MutableStateFlow<List<Filme>>(emptyList())
    val filmes: StateFlow<List<Filme>> = _filmes.asStateFlow()

    private val _detalhes = MutableStateFlow<DetalhesFilme?>(null)
    val detalhes: StateFlow<DetalhesFilme?> = _detalhes.asStateFlow()

    private val _favoritos = MutableStateFlow<List<FilmeFavorito>>(emptyList())
    val favoritos: StateFlow<List<FilmeFavorito>> = _favoritos.asStateFlow()

    init {
        listarFavoritos()
    }

    fun buscarFilmes(chave: String, pesquisa: String) {
        viewModelScope.launch {
            try {
                val resultado = RetrofitInstance.api.buscarFilmes(chave, pesquisa)
                _filmes.value = resultado.filmes ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun buscarDetalhes(chave: String, id: String) {
        viewModelScope.launch {
            try {
                val resultado = RetrofitInstance.api.buscarDetalhes(chave, id)
                _detalhes.value = resultado
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun adicionarFavorito(filme: DetalhesFilme) {
        viewModelScope.launch {
            val favorito = FilmeFavorito(
                id = filme.id,
                titulo = filme.titulo,
                ano = filme.ano,
                genero = filme.genero,
                sinopse = filme.sinopse,
                avaliacao = filme.avaliacao
            )

            dao.inserir(favorito)
            listarFavoritos()
        }
    }

    fun listarFavoritos() {
        viewModelScope.launch {
            _favoritos.value = dao.listarTodos()
        }
    }
}