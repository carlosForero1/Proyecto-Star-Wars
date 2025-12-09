package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.PlanetDetailViewModel

@Composable
fun PlanetDetailScreen(
    viewModel: PlanetDetailViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.uiState.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {
            Button(onClick = onBack) {
                Text("← Volver")
            }

            Spacer(Modifier.height(20.dp))
        }

        item {
            when {
                state.isLoading -> {
                    CircularProgressIndicator()
                }

                state.error != null -> {
                    Text(
                        state.error!!,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                state.planet != null -> {
                    val p = state.planet

                    Text(
                        p.name,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(Modifier.height(12.dp))
                    Divider()
                    Spacer(Modifier.height(16.dp))

                    Text("🌦 Clima: ${p.climate}")
                    Text("🌍 Terreno: ${p.terrain}")
                    Text("👥 Población: ${p.population}")
                    Text("🪐 Gravedad: ${p.gravity}")
                    Text("⏱ Rotación: ${p.rotation_period}")
                    Text("🔄 Órbita: ${p.orbital_period}")
                    Text("📏 Diámetro: ${p.diameter}")
                    Text("💧 Agua superficial: ${p.surface_water}")

                    Spacer(Modifier.height(40.dp))
                }

                else -> {
                    Text("Cargando planeta...")
                }
            }
        }
    }
}
