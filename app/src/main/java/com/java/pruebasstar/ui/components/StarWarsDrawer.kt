package com.java.pruebasstar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.java.pruebasstar.R
import kotlinx.coroutines.launch

@Composable
fun StarWarsDrawer(
    side: String,
    onPersonajes: () -> Unit,
    onPeliculas: () -> Unit,
    onPlanetas: () -> Unit,
    onNaves: () -> Unit,
    onVehiculos: () -> Unit,
    onMenuClick: ((() -> Unit) -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        onMenuClick?.invoke {
            scope.launch { drawerState.open() }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = Color.Transparent,
        drawerContent = {

            ModalDrawerSheet(
                modifier = Modifier.background(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(Color.Black)
                ) {
                    val backgroundImage = if (side == "Jedi") R.drawable.rebelion else R.drawable.sith
                    Image(
                        painter = painterResource(id =backgroundImage),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(Color(0x928d8d))
                    ) {
                        val overlayColor =
                            if (side == "Jedi") Color.Black
                            else Color(0xAAFF0000)
                        Text(
                            text = "MENÚ STAR WARS",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(16.dp)
                        )

                        NavigationDrawerItem(
                            label = { Text("Personajes", color = overlayColor) },
                            selected = false,
                            onClick = { onPersonajes(); scope.launch { drawerState.close() } }
                        )
                        NavigationDrawerItem(
                            label = { Text("Películas", color = overlayColor) },
                            selected = false,
                            onClick = { onPeliculas(); scope.launch { drawerState.close() } }
                        )
                        NavigationDrawerItem(
                            label = { Text("Planetas", color = overlayColor) },
                            selected = false,
                            onClick = { onPlanetas(); scope.launch { drawerState.close() } }
                        )
                        NavigationDrawerItem(
                            label = { Text("Naves", color = overlayColor) },
                            selected = false,
                            onClick = { onNaves(); scope.launch { drawerState.close() } }
                        )
                        NavigationDrawerItem(
                            label = { Text("Vehículos", color = overlayColor) },
                            selected = false,
                            onClick = { onVehiculos(); scope.launch { drawerState.close() } }
                        )
                    }
                }
            }
        }
    ) {
        content()
    }
}
