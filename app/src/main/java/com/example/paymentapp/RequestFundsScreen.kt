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
fun RequestFundsScreen(navController: NavController, viewModel: MainViewModel) {
    var requestAmount by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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
                Text(
                    text = "Request Funds",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                TextField(
                    value = requestAmount,
                    onValueChange = { input ->
                        requestAmount = input
                        // Allow any input, but check if valid numeric input for the error message
                        errorMessage = if (input.isNotEmpty() && !input.all { it.isDigit() || it == '.' }) {
                            "Please enter a valid numeric amount."
                        } else {
                            "" // Clear error message for valid input
                        }
                    },
                    label = { Text("Amount to Request") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Display error message if any
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = Color.Red)
                }

                Button(
                    onClick = {
                        val amount = requestAmount.toDoubleOrNull()
                        if (amount != null) {
                            viewModel.requestFunds(amount) // Handle funds request in ViewModel
                            navController.navigate("confirmation_screen?amount=$amount&isFundRequest=true") // Navigate to confirmation
                        } else {
                            errorMessage = "Please enter a valid amount." // Show error if amount is invalid
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Request Funds")
                }

                // Back to Account Screen Button
                Button(
                    onClick = { navController.navigate("account_screen") },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Back to Account Screen")
                }
            }
        }
    }
}
