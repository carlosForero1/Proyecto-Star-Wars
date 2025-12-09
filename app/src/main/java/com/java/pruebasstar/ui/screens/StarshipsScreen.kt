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
import com.java.pruebasstar.ui.viewmodel.StarshipsViewModel

@Composable
fun StarshipsScreen(
    viewModel: StarshipsViewModel,
    onClick: (String) -> Unit
) {
    val state = viewModel.uiState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A))   // 🌙 Fondo oscuro
            .padding(16.dp)
    ) {

        Text(
            "🚀 Naves Espaciales",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
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
                    "Error: ${state.error}",
                    color = Color.Red
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), // ⬅️ Ocupa toda la pantalla restante
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.films) { ship ->

                        Surface(
                            color = Color.White.copy(alpha = 0.07f),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onClick(ship.id) }
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    ship.name,
                                    color = Color.White,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Spacer(Modifier.height(6.dp))

                                Text(
                                    "Modelo: ${ship.model}",
                                    color = Color.LightGray,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
