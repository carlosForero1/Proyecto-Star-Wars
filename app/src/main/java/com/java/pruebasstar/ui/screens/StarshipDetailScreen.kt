package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.StarshipDetailViewModel

@Composable
fun StarshipDetailScreen(
    viewModel: StarshipDetailViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.uiState.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A)) // ⬅️ Fondo oscuro como los demás
            .padding(16.dp)
    ) {

        item {
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1C1C1C),
                    contentColor = Color.White
                )
            ) {
                Text("← Volver")
            }

            Spacer(Modifier.height(20.dp))
        }

        item {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(color = Color.White)
                }

                state.error != null -> {
                    Text(
                        "Error: ${state.error}",
                        color = Color.Red
                    )
                }

                state.ship != null -> {
                    val s = state.ship

                    // Nombre grande
                    Text(
                        s.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )

                    Spacer(Modifier.height(12.dp))
                    Divider(color = Color.Gray.copy(alpha = 0.4f))
                    Spacer(Modifier.height(16.dp))

                    DetailRow("Modelo", s.model)
                    DetailRow("Fabricante", s.manufacturer)
                    DetailRow("Costo", s.cost_in_credits)
                    DetailRow("Longitud", s.length)
                    DetailRow("Velocidad", s.max_atmosphering_speed)
                    DetailRow("Tripulación", s.crew)
                    DetailRow("Pasajeros", s.passengers)
                    DetailRow("Carga", s.cargo_capacity)

                    Spacer(Modifier.height(40.dp))
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Column(Modifier.padding(vertical = 6.dp)) {
        Text(
            "$label:",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.LightGray
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
        Spacer(Modifier.height(6.dp))
    }
}
