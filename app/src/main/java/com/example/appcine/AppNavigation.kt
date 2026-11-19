package com.example.appcine

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appcine.viewmodel.FilmeViewModel

sealed class Screen(val route: String) {
    object Inicio : Screen("inicio")
    object ListaFilmes : Screen("lista_filmes")
    object Favoritos : Screen("favoritos")
    object Detalhes : Screen("detalhes/{id}")
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val filmeViewModel: FilmeViewModel = viewModel()

    val chave = "digite aqui a sua chave gerada pelo omdb"

    NavHost(
        navController = navController,
        startDestination = Screen.Inicio.route
    ) {

        composable(Screen.Inicio.route) {
            TelaInicial(
                onListaFilmesClick = {
                    navController.navigate(Screen.ListaFilmes.route)
                },
                onFavoritosClick = {
                    navController.navigate(Screen.Favoritos.route)
                }
            )
        }

        composable(Screen.ListaFilmes.route) {
            TelaListaFilmes(
                viewModel = filmeViewModel,
                chave = chave,
                onFilmeClick = { id ->
                    navController.navigate("detalhes/$id")
                }
            )
        }

        composable(Screen.Favoritos.route) {
            TelaFavoritos(viewModel = filmeViewModel)
        }

        composable(
            route = Screen.Detalhes.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { entrada ->

            val id = entrada.arguments?.getString("id") ?: ""

            TelaDetalhesFilme(
                viewModel = filmeViewModel,
                chave = chave,
                id = id
            )
        }
    }
}