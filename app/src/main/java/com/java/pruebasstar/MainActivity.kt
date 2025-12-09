package com.java.pruebasstar

import android.media.MediaPlayer
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
import com.java.pruebasstar.data.remote.ApiClient
import com.java.pruebasstar.data.repository.StarWarsRepository
import com.java.pruebasstar.ui.navigation.Screen
import com.java.pruebasstar.ui.screens.*
import com.java.pruebasstar.ui.viewmodel.*

class MainActivity : ComponentActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🎵 Iniciar música
        mediaPlayer = MediaPlayer.create(this, R.raw.intro).apply {
            isLooping = true     // 🔁 Repetir canción infinitamente
            start()
        }

        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Login.route
                ) {

                    // -------------------------
                    // LOGIN
                    // -------------------------
                    composable(Screen.Login.route) {
                        LoginScreen { name, side ->
                            navController.navigate(Screen.Home.createRoute(name, side))
                        }
                    }


                    composable(Screen.Home.route) { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("name") ?: ""
                        val side = backStackEntry.arguments?.getString("side") ?: ""

                        HomeScreen(
                            name = name,
                            side = side,
                            onPersonajes = { navController.navigate(Screen.Characters.route) },
                            onPeliculas = { navController.navigate(Screen.Films.route) },
                            onPlanetas = { navController.navigate(Screen.Planets.route) },
                            onNaves = { navController.navigate(Screen.Starships.route) },
                            onVehiculos = { navController.navigate(Screen.Vehicles.route) }
                        )
                    }

                    // -------------------------
                    // PERSONAJES LISTA
                    // -------------------------
                    composable(Screen.Characters.route) {
                        val repo = remember { StarWarsRepository(ApiClient.apiService) }
                        val vm: CharacterViewModel =
                            viewModel(factory = CharacterViewModel.Factory(repo))

                        CharactersScreen(
                            viewModel = vm,
                            onCharacterClick = { id ->
                                navController.navigate(Screen.CharacterDetail.createRoute(id))
                            }
                        )
                    }

                    // -------------------------
                    // PERSONAJE DETALLE
                    // -------------------------
                    composable(Screen.CharacterDetail.route) { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id") ?: "1"
                        val repo = remember { StarWarsRepository(ApiClient.apiService) }
                        val vm: CharacterDetailViewModel =
                            viewModel(factory = CharacterDetailViewModel.Factory(repo))

                        LaunchedEffect(id) { vm.loadCharacterDetail(id) }

                        CharacterDetailScreen(
                            viewModel = vm,
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // -------------------------
                    // PELÍCULAS LISTA
                    // -------------------------
                    composable(Screen.Films.route) {
                        val repo = remember { StarWarsRepository(ApiClient.apiService) }
                        val vm: FilmsViewModel = viewModel(factory = FilmsViewModel.Factory(repo))

                        FilmsScreen(
                            viewModel = vm,
                            onFilmClick = { id ->
                                navController.navigate(Screen.FilmDetail.createRoute(id))
                            }
                        )
                    }

                    // -------------------------
                    // PELÍCULA DETALLE
                    // -------------------------
                    composable(Screen.FilmDetail.route) { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id") ?: "1"
                        val repo = remember { StarWarsRepository(ApiClient.apiService) }
                        val vm: FilmDetailViewModel =
                            viewModel(factory = FilmDetailViewModel.Factory(repo))

                        LaunchedEffect(id) { vm.loadFilmDetail(id) }

                        FilmDetailScreen(
                            viewModel = vm,
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // -------------------------
                    // PLANETAS LISTA
                    // -------------------------
                    composable(Screen.Planets.route) {
                        val repo = remember { StarWarsRepository(ApiClient.apiService) }
                        val vm: PlanetsViewModel =
                            viewModel(factory = PlanetsViewModel.Factory(repo))

                        PlanetsScreen(
                            viewModel = vm,
                            onPlanetClick = { id ->
                                navController.navigate(Screen.PlanetDetail.createRoute(id))
                            }
                        )
                    }

                    // -------------------------
                    // PLANETA DETALLE
                    // -------------------------
                    composable(Screen.PlanetDetail.route) { bse ->
                        val id = bse.arguments?.getString("id") ?: "1"
                        val repo = remember { StarWarsRepository(ApiClient.apiService) }
                        val vm: PlanetDetailViewModel =
                            viewModel(factory = PlanetDetailViewModel.Factory(repo))

                        LaunchedEffect(id) { vm.loadPlanetDetail(id) }

                        PlanetDetailScreen(
                            viewModel = vm,
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // -------------------------
                    // NAVES / STARSHIPS
                    // -------------------------
                    composable(Screen.Starships.route) {
                        val repo = remember { StarWarsRepository(ApiClient.apiService) }
                        val vm: StarshipsViewModel =
                            viewModel(factory = StarshipsViewModel.Factory(repo))

                        StarshipsScreen(
                            viewModel = vm,
                            onClick = { id ->
                                navController.navigate(Screen.StarshipDetail.createRoute(id))
                            }
                        )
                    }

                    composable(Screen.StarshipDetail.route) { bse ->
                        val id = bse.arguments?.getString("id") ?: "1"
                        val repo = remember { StarWarsRepository(ApiClient.apiService) }
                        val vm: StarshipDetailViewModel =
                            viewModel(factory = StarshipDetailViewModel.Factory(repo))

                        LaunchedEffect(id) { vm.loadStarshipDetail(id) }

                        StarshipDetailScreen(
                            viewModel = vm,
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // -------------------------
                    // VEHÍCULOS LISTA
                    // -------------------------
                    composable(Screen.Vehicles.route) {
                        val repo = remember { StarWarsRepository(ApiClient.apiService) }
                        val vm: VehiclesViewModel =
                            viewModel(factory = VehiclesViewModel.Factory(repo))

                        VehiclesScreen(
                            viewModel = vm,
                            onClick = { id ->
                                navController.navigate(Screen.VehicleDetail.createRoute(id))
                            }
                        )
                    }

                    // -------------------------
                    // VEHÍCULO DETALLE
                    // -------------------------
                    composable(Screen.VehicleDetail.route) { bse ->
                        val id = bse.arguments?.getString("id") ?: "1"
                        val repo = remember { StarWarsRepository(ApiClient.apiService) }
                        val vm: VehicleDetailViewModel =
                            viewModel(factory = VehicleDetailViewModel.Factory(repo))

                        LaunchedEffect(id) { vm.loadVehicleDetail(id) }

                        VehicleDetailScreen(
                            viewModel = vm,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // 🧹 liberar memoria
    }
}
