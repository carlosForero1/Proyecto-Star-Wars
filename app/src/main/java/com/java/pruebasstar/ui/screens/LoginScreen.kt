package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.java.pruebasstar.R
import com.java.pruebasstar.ui.Login.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: (String, String) -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Fondo JPG
        Image(
            painter = painterResource(id = R.drawable.fondo_login),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Oscurecer un poco para leer mejor
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "STAR WARS LOGIN",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Spacer(Modifier.height(20.dp))

            // TextField negro con texto blanco
            TextField(
                value = viewModel.name,
                onValueChange = { viewModel.onNameChanged(it) },

                label = { Text("Tu nombre", color = Color.White) },

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Black.copy(alpha = 0.7f),
                    unfocusedContainerColor = Color.Black.copy(alpha = 0.7f),

                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,

                    cursorColor = Color.White,

                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),

                singleLine = true
            )

            Spacer(Modifier.height(20.dp))

            Text(
                "Elige tu lado:",
                color = Color.White
            )

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = { viewModel.onSideSelected("Jedi") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Bueno")
                }

                Button(
                    onClick = { viewModel.onSideSelected("Sith") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Malo")
                }
            }

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    onLoginSuccess(viewModel.name, viewModel.side)
                },
                enabled = viewModel.canLogin(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    disabledContainerColor = Color.DarkGray,
                    disabledContentColor = Color.LightGray
                )
            ) {
                Text("Ingresar")
            }
        }
    }
}
