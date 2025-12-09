package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.FilmsViewModel

@Composable
fun FilmsScreen(
    viewModel: FilmsViewModel,
    onFilmClick: (String) -> Unit
) {
    val state = viewModel.uiState.value

    // Fondo que sí cubre toda la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                "PELÍCULAS DE STAR WARS",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.White)
                    }
                }

                state.error != null -> {
                    Text(
                        text = state.error!!,
                        color = Color.Red
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(state.films) { film ->
                            Surface(
                                color = Color.White.copy(alpha = 0.06f),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .clickable { onFilmClick(film.id) }
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(film.title, color = Color.White)
                                    Text("Episodio: ${film.episode_id}", color = Color.Gray)
                                    Text("Estreno: ${film.release_date}", color = Color.Gray)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
