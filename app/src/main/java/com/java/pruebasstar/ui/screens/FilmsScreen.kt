package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.FilmsViewModel

@Composable
fun FilmsScreen(
    viewModel: FilmsViewModel,
    onFilmClick: (String) -> Unit
) {
    val state = viewModel.uiState.value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("PELÍCULAS DE STAR WARS", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Text(state.error!!, color = MaterialTheme.colorScheme.error)
            }

            else -> {
                LazyColumn {
                    items(state.films) { film ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onFilmClick(film.id) }
                                .padding(12.dp)
                        ) {
                            Text(film.title, style = MaterialTheme.typography.titleMedium)
                            Text("Episodio: ${film.episode_id}")
                            Text("Estreno: ${film.release_date}")
                        }
                        Divider()
                    }
                }
            }
        }
    }
}
