package com.example.paymentapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    // State variables for username, password, and error message
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Centering the content using Box
    Box(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen
            .padding(16.dp), // Padding around the content
        contentAlignment = Alignment.Center // Center the content
    ) {
        // Column to arrange elements vertically
        Column(
            modifier = Modifier.fillMaxWidth(), // Make the column take full width
            horizontalAlignment = Alignment.CenterHorizontally, // Center children horizontally
            verticalArrangement = Arrangement.spacedBy(16.dp) // Space between elements
        ) {
            // Username TextField
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth() // Make the TextField full width
            )
            // Password TextField
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth() // Make the TextField full width
            )
            // Display error message if any
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            }
            // Login Button
            Button(
                onClick = {
                    // Handle login logic
                    if (username == "test" && password == "test") {
                        navController.navigate("account_screen") // Navigate to Account Screen
                    } else {
                        errorMessage = "Incorrect username or password."
                    }
                },
                modifier = Modifier.fillMaxWidth() // Make the Button full width
            ) {
                Text("Login")
            }
        }
    }
}
