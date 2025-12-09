package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.FilmDetailViewModel

@Composable
fun FilmDetailScreen(
    viewModel: FilmDetailViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.uiState.value
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()            // ⬅️ Toma toda la pantalla
            .padding(16.dp)
            .verticalScroll(scrollState)  // ⬅️ Scroll para textos largos
    ) {

        // 🔙 Botón volver
        Button(onClick = onBack) {
            Text("← Volver")
        }

        Spacer(Modifier.height(20.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Text(state.error!!, color = MaterialTheme.colorScheme.error)
            }

            state.film != null -> {
                val film = state.film

                Text(
                    film.title,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(Modifier.height(12.dp))
                Divider()

                Spacer(Modifier.height(12.dp))

                // INFO PRINCIPAL
                Text("🎬 Director: ${film.director}")
                Text("🎞 Productor: ${film.producer}")
                Text("📺 Episodio: ${film.episode_id}")
                Text("📅 Fecha de estreno: ${film.release_date}")

                Spacer(Modifier.height(24.dp))
                Divider()
                Spacer(Modifier.height(16.dp))

                // OPENING CRAWL
                Text(
                    "Opening Crawl:",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    film.opening_crawl,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            else -> {
                Text("Cargando detalles...")
            }
        }
    }
}
