package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.StarshipsViewModel

@Composable
fun StarshipsScreen(
    viewModel: StarshipsViewModel,
    onClick: (String) -> Unit
) {
    val state = viewModel.uiState.value

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Text("Naves", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> Text("Error: ${state.error}")
            else -> LazyColumn {
                items(state.films) { ship ->
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .clickable { onClick(ship.id) }
                            .padding(12.dp)
                    ) {
                        Text(ship.name, style = MaterialTheme.typography.titleMedium)
                        Text("Modelo: ${ship.model}")
                    }
                    Divider()
                }
            }
        }
    }
}
