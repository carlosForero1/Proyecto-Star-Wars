package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.VehiclesViewModel

@Composable
fun VehiclesScreen(
    viewModel: VehiclesViewModel,
    onClick: (String) -> Unit
) {
    val state = viewModel.uiState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A)) // Fondo oscuro
            .padding(16.dp)
    ) {

        Text(
            "VEHÍCULOS",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )

        Spacer(Modifier.height(16.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator(color = Color.White)
            }

            state.error != null -> {
                Text(
                    state.error ?: "Error",
                    color = Color.Red
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f) // Ocupa todo el espacio restante
                ) {
                    items(state.vehicles) { v ->

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clickable { onClick(v.id) },
                            shape = RoundedCornerShape(12.dp),
                            color = Color.White.copy(alpha = 0.06f) // Card oscura
                        ) {

                            Column(
                                Modifier.padding(16.dp)
                            ) {
                                Text(
                                    v.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )

                                Spacer(Modifier.height(6.dp))

                                Text(
                                    "Modelo: ${v.model}",
                                    color = Color.LightGray
                                )

                                Text(
                                    "Fabricante: ${v.manufacturer}",
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
