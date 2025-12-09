package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.PlanetsViewModel

@Composable
fun PlanetsScreen(
    viewModel: PlanetsViewModel,
    onPlanetClick: (String) -> Unit
) {
    val state = viewModel.uiState.value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("PLANETAS DE STAR WARS", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        when {
            state.isLoading -> CircularProgressIndicator()

            state.error != null -> Text(state.error!!)

            else -> {
                LazyColumn {
                    items(state.planets) { planet ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onPlanetClick(planet.id) }
                                .padding(12.dp)
                        ) {
                            Text(planet.name, style = MaterialTheme.typography.titleMedium)
                            Text("Terreno: ${planet.terrain}")
                            Text("Clima: ${planet.climate}")
                        }
                        Divider()
                    }
                }
            }
        }
    }
}
