package com.java.pruebasstar.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.VehicleDetailViewModel

@Composable
fun VehicleDetailScreen(
    viewModel: VehicleDetailViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.uiState.value

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(onClick = onBack) { Text("← Volver") }
        Spacer(Modifier.height(20.dp))

        when {
            state.isLoading -> CircularProgressIndicator()

            state.error != null -> Text(state.error!!)

            state.vehicle != null -> {
                val v = state.vehicle

                Text(v.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(12.dp))
                Divider()
                Spacer(Modifier.height(16.dp))

                Text("Modelo: ${v.model}")
                Text("Fabricante: ${v.manufacturer}")
                Text("Costo: ${v.cost_in_credits}")
                Text("Velocidad: ${v.max_atmosphering_speed}")
                Text("Tripulación: ${v.crew}")
                Text("Pasajeros: ${v.passengers}")
            }

            else -> Text("Cargando vehículo...")
        }
    }
}
