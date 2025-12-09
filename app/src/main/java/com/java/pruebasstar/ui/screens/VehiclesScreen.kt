package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.VehiclesViewModel

@Composable
fun VehiclesScreen(
    viewModel: VehiclesViewModel,
    onClick: (String) -> Unit
) {
    val state = viewModel.uiState.value

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Text("VEHÍCULOS", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        when {
            state.isLoading -> CircularProgressIndicator()

            state.error != null -> Text(state.error!!, color = MaterialTheme.colorScheme.error)

            else -> {
                LazyColumn {
                    items(state.vehicles) { v ->
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .clickable { onClick(v.id) }
                                .padding(12.dp)
                        ) {
                            Text(v.name, style = MaterialTheme.typography.titleMedium)
                            Text("Modelo: ${v.model}")
                            Text("Fabricante: ${v.manufacturer}")
                        }
                        Divider()
                    }
                }
            }
        }
    }
}
