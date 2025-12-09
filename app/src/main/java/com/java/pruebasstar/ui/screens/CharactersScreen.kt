package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.ui.viewmodel.CharacterViewModel

@Composable
fun CharactersScreen(
    viewModel: CharacterViewModel,
    onCharacterClick: (String) -> Unit
) {
    val state = viewModel.uiState.value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text(
            "PERSONAJES DE STAR WARS",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 🔍 Barra de búsqueda
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { viewModel.onSearchChange(it) },
            placeholder = { Text("Buscar personaje...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            state.error != null -> {
                Text(
                    text = state.error ?: "Error",
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> {
                // LISTA DE PERSONAJES
                LazyColumn {
                    items(viewModel.filteredCharacters()) { character ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                                .clickable { onCharacterClick(character.id) },
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(character.name, style = MaterialTheme.typography.bodyLarge)
                            Text("->")
                        }
                        Divider()
                    }
                }
            }
        }
    }
}
