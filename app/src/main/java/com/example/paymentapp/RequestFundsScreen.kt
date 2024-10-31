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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RequestFundsScreen(navController: NavController, viewModel: MainViewModel) {
    var requestAmount by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.testTag("RequestFundsScreenTag") // Added to identify the screen for testing
    ) { contentPadding ->
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
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.testTag("RequestFundsTitle") // Test Tag for Title
                )

                TextField(
                    value = requestAmount,
                    onValueChange = { input ->
                        requestAmount = input
                        errorMessage = if (input.isNotEmpty() && !input.all { it.isDigit() || it == '.' }) {
                            "Please enter a valid numeric amount."
                        } else {
                            ""
                        }
                    },
                    label = { Text("Amount to Request") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("PaymentAmountField") // Test Tag for TextField
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.testTag("ErrorMessageTag") // Test Tag for Error Message
                    )
                }

                Button(
                    onClick = {
                        val amount = requestAmount.toDoubleOrNull()
                        if (amount != null) {
                            viewModel.requestFunds(amount)
                            navController.navigate("confirmation_screen?amount=$amount&isFundRequest=true")
                        } else {
                            errorMessage = "Please enter a valid amount."
                        }
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .testTag("SubmitPaymentButton") // Test Tag for Button
                ) {
                    Text("Request Funds")
                }

                Button(
                    onClick = { navController.navigate("account_screen") },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .testTag("BackToAccountButton") // Test Tag for Back Button
                ) {
                    Text("Back to Account Screen")
                }
            }
        }
    }
}
