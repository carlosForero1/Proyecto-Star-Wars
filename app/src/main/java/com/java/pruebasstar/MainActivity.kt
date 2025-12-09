package com.java.pruebasstar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.java.pruebasstar.ui.navigation.Screen
import com.java.pruebasstar.ui.screens.HomeScreen
import com.java.pruebasstar.ui.screens.LoginScreen
import com.java.pruebasstar.ui.screens.CharactersScreen
import com.java.pruebasstar.data.remote.ApiClient
import com.java.pruebasstar.data.repository.StarWarsRepository
import com.java.pruebasstar.ui.screens.CharacterDetailScreen
import com.java.pruebasstar.ui.viewmodel.CharacterDetailViewModel
import com.java.pruebasstar.ui.viewmodel.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {

                val navController = rememberNavController()

                // ⭐ AQUÍ VA EL NAVHOST COMPLETO
                NavHost(
                    navController = navController,
                    startDestination = Screen.Login.route
                ) {

                    // LOGIN
                    composable(Screen.Login.route) {
                        LoginScreen { name, side ->
                            navController.navigate(Screen.Home.createRoute(name, side))
                        }
                    }

                    // HOME
                    composable(Screen.Home.route) { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("name") ?: ""
                        val side = backStackEntry.arguments?.getString("side") ?: ""

                        HomeScreen(
                            name = name,
                            side = side,
                            onPersonajes = { navController.navigate(Screen.Characters.route) },
                            onPeliculas = { navController.navigate("peliculas") },
                            onPlanetas = { navController.navigate("planetas") },
                            onNaves = { navController.navigate("naves") }
                        )
                    }

                    // LISTA DE PERSONAJES
                    composable(Screen.Characters.route) {

                        val repo = remember { StarWarsRepository(ApiClient.apiService) }

                        val vm: CharacterViewModel = viewModel(
                            factory = CharacterViewModel.Factory(repo)
                        )

                        CharactersScreen(
                            viewModel = vm,
                            onCharacterClick = { id ->
                                navController.navigate(Screen.CharacterDetail.createRoute(id))
                            }
                        )
                    }

                    // DETALLE DEL PERSONAJE  ⭐ **CORRECTO AHORA**
                    composable(Screen.CharacterDetail.route) { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id") ?: "1"

                        val repo = remember { StarWarsRepository(ApiClient.apiService) }

                        val vm: CharacterDetailViewModel = viewModel(
                            factory = CharacterDetailViewModel.Factory(repo)
                        )

                        LaunchedEffect(id) {
                            vm.loadCharacterDetail(id)
                        }

                        CharacterDetailScreen(
                            viewModel = vm,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}