package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.StarshipDetailViewModel

@Composable
fun StarshipDetailScreen(
    viewModel: StarshipDetailViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.uiState.value

    LazyColumn(Modifier.fillMaxSize().padding(16.dp)) {

        item {
            Button(onClick = onBack) {
                Text("← Volver")
            }
            Spacer(Modifier.height(20.dp))
        }

        item {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text("Error: ${state.error}")
                state.ship != null -> {
                    val s = state.ship

                    Text(s.name, style = MaterialTheme.typography.headlineMedium)
                    Spacer(Modifier.height(10.dp))
                    Divider()
                    Spacer(Modifier.height(10.dp))

                    Text("Modelo: ${s.model}")
                    Text("Fabricante: ${s.manufacturer}")
                    Text("Costo: ${s.cost_in_credits}")
                    Text("Longitud: ${s.length}")
                    Text("Velocidad: ${s.max_atmosphering_speed}")
                    Text("Tripulación: ${s.crew}")
                    Text("Pasajeros: ${s.passengers}")
                    Text("Carga: ${s.cargo_capacity}")
                }
            }
        }
    }
}
