package com.example.appcine

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcine.viewmodel.FilmeViewModel

@Composable
fun TelaListaFilmes(
    viewModel: FilmeViewModel,
    chave: String,
    onFilmeClick: (String) -> Unit
) {
    val filmes by viewModel.filmes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.buscarFilmes(chave, "Wicked")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(filmes) { filme ->
            Column(
                modifier = Modifier.clickable {
                    onFilmeClick(filme.id)
                }
            ) {
                Text(text = filme.titulo)
                Text(text = filme.ano)
            }
        }
    }
}