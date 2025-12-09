package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            .background(Color(0xFF0A0A0A))   // Fondo oscuro
            .padding(16.dp)
    ) {

        item {
            OutlinedButton(
                onClick = onBack,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                )
            ) {
                Text("Volver")
            }

            Spacer(Modifier.height(20.dp))
        }

        item {
            when {
                state.isLoading -> CircularProgressIndicator(color = Color.White)

                state.error != null -> Text(
                    text = state.error ?: "Error al cargar",
                    color = Color.Red
                )

                state.planet != null -> {
                    val p = state.planet

                    Text(
                        p.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )

                    Spacer(Modifier.height(12.dp))
                    Divider(color = Color.Gray.copy(alpha = 0.4f))
                    Spacer(Modifier.height(16.dp))

                    PlanetInfoRow("Clima", p.climate)
                    PlanetInfoRow("Terreno", p.terrain)
                    PlanetInfoRow("Población", p.population)
                    PlanetInfoRow("Gravedad", p.gravity)
                    PlanetInfoRow("Rotación", p.rotation_period)
                    PlanetInfoRow("Órbita", p.orbital_period)
                    PlanetInfoRow("Diámetro", p.diameter)
                    PlanetInfoRow("Agua superficial", p.surface_water)

                    Spacer(Modifier.height(40.dp))
                }

                else -> Text("Cargando planeta...", color = Color.White)
            }
        }
    }
}

@Composable
fun PlanetInfoRow(label: String, value: String) {
    Column(
        Modifier
            .padding(vertical = 6.dp)
    ) {
        Text(
            "$label: $value",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.LightGray
        )
    }
}
