package com.example.appcine

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcine.viewmodel.FilmeViewModel

@Composable
fun TelaFavoritos(viewModel: FilmeViewModel) {

    val favoritos by viewModel.favoritos.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = "Meus Favoritos")

        Spacer(modifier = Modifier.height(16.dp))

        if (favoritos.isEmpty()) {

            Text(text = "Nenhum filme favorito.")

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favoritos) { filme ->

                    Column {
                        Text(text = filme.titulo)
                        Text(text = filme.ano)
                        Text(text = "Gênero: ${filme.genero}")
                        Text(text = "Avaliação: ${filme.avaliacao}")
                    }
                }
            }
        }
    }
}