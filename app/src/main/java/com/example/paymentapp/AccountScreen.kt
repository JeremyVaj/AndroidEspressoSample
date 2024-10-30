package com.example.paymentapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AccountScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Display balance with larger font size
                Text(
                    text = "Balance: $${viewModel.balance.value}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                // Payment amount input
                var paymentAmount by remember { mutableStateOf("") }
                var errorMessage by remember { mutableStateOf("") }

                TextField(
                    value = paymentAmount,
                    onValueChange = { input ->
                        paymentAmount = input
                        errorMessage = if (input.isNotEmpty() && !input.all { it.isDigit() || it == '.' }) {
                            "Please enter a valid numeric amount."
                        } else {
                            "" // Clear error message for valid input
                        }
                    },
                    label = { Text("Payment Amount") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Display error message if any
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = Color.Red)
                }

                // Submit payment button
                Button(
                    onClick = {
                        val amount = paymentAmount.toDoubleOrNull()
                        if (amount != null) {
                            val error = viewModel.submitPayment(amount) // Handle payment submission
                            if (error == null) {
                                navController.navigate("confirmation_screen?amount=${amount}") // Navigate on success
                            } else {
                                errorMessage = error // Show error if payment submission fails
                            }
                        } else {
                            errorMessage = "Please enter a valid amount." // Show error if amount is invalid
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Submit Payment")
                }

                // Request funds button
                Button(
                    onClick = {
                        navController.navigate("request_funds_screen") // Navigate to request funds screen
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Request Funds")
                }
            }

            // Log Out button at the bottom
            Button(
                onClick = {
                    navController.navigate("login_screen") // Adjust to your actual login screen route
                },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text("Log Out")
            }
        }
    }
}
