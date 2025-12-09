package com.java.pruebasstar.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.java.pruebasstar.ui.Login.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: (String, String) -> Unit

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("STAR WARS LOGIN", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(20.dp))

        TextField(
            value = viewModel.name,
            onValueChange = { viewModel.onNameChanged(it) },
            label = { Text("Tu nombre") },
            singleLine = true
        )

        Spacer(Modifier.height(20.dp))

        Text("Elige tu lado:")

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { viewModel.onSideSelected("Jedi") }) {
                Text("Bueno")
            }
            Button(onClick = { viewModel.onSideSelected("Sith") }) {
                Text("Malo")
            }
        }

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = {
                onLoginSuccess(viewModel.name, viewModel.side)
            },
            enabled = viewModel.canLogin()
        ) {
            Text("Ingresar")
        }
    }
}
