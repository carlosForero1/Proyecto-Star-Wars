package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.java.pruebasstar.R

@Composable
fun HomeScreen(
    name: String,
    side: String,
    onPersonajes: () -> Unit,
    onPeliculas: () -> Unit,
    onPlanetas: () -> Unit,
    onNaves: () -> Unit,
    onVehiculos: () -> Unit
) {

    // Fondo según el lado
    val backgroundImage = if (side == "Jedi") {
        painterResource(id = R.drawable.rebelion)
    } else {
        painterResource(id = R.drawable.sith)
    }

    val overlayColor = if (side == "Jedi") {
        Color.Black.copy(alpha = 0.55f)
    } else {
        Color(0xFF8B0000).copy(alpha = 0.55f)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // IMAGEN DE FONDO
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        // CONTENIDO
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Bienvenido $name",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Lado: $side",
                fontSize = 22.sp,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(40.dp))

            HomeButton("Personajes", onPersonajes)
            HomeButton("Películas", onPeliculas)
            HomeButton("Planetas", onPlanetas)
            HomeButton("Naves", onNaves)
            HomeButton("Vehículos", onVehiculos)
        }
    }
}

@Composable
fun HomeButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { onClick() }
            .padding(15.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFF505050).copy(alpha = 0.75f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text, color = Color.White, fontSize = 20.sp)
            }
        }
    }
}
