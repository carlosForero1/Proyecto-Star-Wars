package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    name: String,
    side: String,
    onPersonajes: () -> Unit,
    onPeliculas: () -> Unit,
    onPlanetas: () -> Unit,
    onNaves: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    val backgroundColor = if (side == "Jedi") Color(0xFF1E88E5) else Color(0xFFB71C1C)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {

                Text(
                    text = "Menú Star Wars",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )

                NavigationDrawerItem(
                    label = { Text("Personajes") },
                    selected = false,
                    onClick = {
                        onPersonajes()
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Películas") },
                    selected = false,
                    onClick = {
                        onPeliculas()
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Planetas") },
                    selected = false,
                    onClick = {
                        onPlanetas()
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Naves / Vehículos") },
                    selected = false,
                    onClick = {
                        onNaves()
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {



            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "STAR WARS UNIVERSE EXPLORER",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            IconButton(
                onClick = {
                    scope.launch {
                        if (drawerState.isClosed) drawerState.open()
                        else drawerState.close()
                    }
                },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(Icons.Default.Menu, contentDescription = "Menú", tint = Color.White)
            }
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Bienvenido $name",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}
