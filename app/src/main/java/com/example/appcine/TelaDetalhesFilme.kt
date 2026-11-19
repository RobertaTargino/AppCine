package com.example.appcine

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcine.viewmodel.FilmeViewModel

@Composable
fun TelaDetalhesFilme(
    viewModel: FilmeViewModel,
    chave: String,
    id: String
) {
    val detalhes by viewModel.detalhes.collectAsState()

    LaunchedEffect(id) {
        viewModel.buscarDetalhes(chave, id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (detalhes != null && detalhes?.id == id) {
            Text(text = "Título: ${detalhes?.titulo}")
            Text(text = "Ano: ${detalhes?.ano}")
            Text(text = "Gênero: ${detalhes?.genero}")
            Text(text = "Sinopse: ${detalhes?.sinopse}")
            Text(text = "Avaliação: ${detalhes?.avaliacao}")

            Button(onClick = {
                detalhes?.let { filme ->
                    viewModel.adicionarFavorito(filme)
                }
            }) {
                Text("Adicionar aos Favoritos")
            }
        } else {
            Text("Carregando detalhes...")
        }
    }
}