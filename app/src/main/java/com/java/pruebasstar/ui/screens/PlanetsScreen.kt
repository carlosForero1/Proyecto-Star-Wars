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
import com.java.pruebasstar.ui.viewmodel.PlanetsViewModel

@Composable
fun PlanetsScreen(
    viewModel: PlanetsViewModel,
    onPlanetClick: (String) -> Unit
) {
    val state = viewModel.uiState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A))   // ⬅️ FONDO OSCURO
            .padding(16.dp)
    ) {

        Text(
            "🌌 Planetas de Star Wars",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White   // ⬅️ Texto blanco
        )

        Spacer(Modifier.height(16.dp))

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
                    state.error!!,
                    color = Color.Red
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),   // ⬅️ Ocupa toda la pantalla
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.planets) { planet ->

                        Surface(
                            color = Color.White.copy(alpha = 0.06f),  // ⬅️ Tarjeta estilo Star Wars
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onPlanetClick(planet.id) }
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    planet.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )

                                Spacer(Modifier.height(8.dp))

                                Text(
                                    "Terreno: ${planet.terrain}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.LightGray
                                )

                                Text(
                                    "Clima: ${planet.climate}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.LightGray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
