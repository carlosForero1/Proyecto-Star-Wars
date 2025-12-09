package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.FilmDetailViewModel

@Composable
fun FilmDetailScreen(
    viewModel: FilmDetailViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.uiState.value
    val scrollState = rememberScrollState()

    // 🎨 Fondo oscuro uniforme
    val bg = Color(0xFF111111)
    val cardBg = Color(0xFF1E1E1E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        // ⬅️ Botón volver estilo Star Wars
        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF333333),
                contentColor = Color.White
            ),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("Volver")
        }

        Spacer(Modifier.height(20.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator(color = Color.White)
            }

            state.error != null -> {
                Text(
                    state.error!!,
                    color = Color(0xFFFF6B6B)
                )
            }

            state.film != null -> {
                val film = state.film

                Text(
                    film.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )

                Spacer(Modifier.height(12.dp))

                Surface(
                    color = cardBg,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Director: ${film.director}", color = Color.White)
                        Text("Productor: ${film.producer}", color = Color.White)
                        Text("Episodio: ${film.episode_id}", color = Color.White)
                        Text("Estreno: ${film.release_date}", color = Color.White)
                    }
                }

                Spacer(Modifier.height(24.dp))

                Text(
                    "Opening Crawl:",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

                Spacer(Modifier.height(10.dp))

                Surface(
                    color = cardBg,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = film.opening_crawl,
                        modifier = Modifier.padding(16.dp),
                        color = Color.White
                    )
                }
            }

            else -> {
                Text("Cargando...", color = Color.White)
            }
        }
    }
}
