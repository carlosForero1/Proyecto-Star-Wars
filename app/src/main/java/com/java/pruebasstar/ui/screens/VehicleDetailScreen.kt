package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.VehicleDetailViewModel

@Composable
fun VehicleDetailScreen(
    viewModel: VehicleDetailViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.uiState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A))   // Fondo oscuro
            .padding(16.dp)
    ) {

        // Botón volver
        OutlinedButton(
            onClick = onBack,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White
            )
        ) {
            Text( "Volver")
        }

        Spacer(Modifier.height(20.dp))

        when {

            state.isLoading -> {
                CircularProgressIndicator(color = Color.White)
            }

            state.error != null -> {
                Text(
                    state.error!!,
                    color = MaterialTheme.colorScheme.error
                )
            }

            state.vehicle != null -> {
                val v = state.vehicle

                // Título del vehículo
                Text(
                    v.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )

                Spacer(Modifier.height(12.dp))
                Divider(color = Color.White.copy(alpha = 0.3f))
                Spacer(Modifier.height(16.dp))

                VehicleInfo("Modelo", v.model)
                VehicleInfo("Fabricante", v.manufacturer)
                VehicleInfo("Costo", v.cost_in_credits)
                VehicleInfo("Velocidad", v.max_atmosphering_speed)
                VehicleInfo("Tripulación", v.crew)
                VehicleInfo("Pasajeros", v.passengers)
            }

            else -> {
                Text("Cargando vehículo...", color = Color.Gray)
            }
        }
    }
}

@Composable
fun VehicleInfo(label: String, value: String) {
    Column(Modifier.padding(vertical = 6.dp)) {
        Text(
            "$label:",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
        Spacer(Modifier.height(8.dp))
    }
}
