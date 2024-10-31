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
import androidx.compose.ui.platform.testTag // Import for testTag

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
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("AccountScreenTag"), // Tag for testing
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Display balance with larger font size
                Text(
                    text = "Balance: $${viewModel.balance.value}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.testTag("AccountScreenBalanceTag") // Tag for balance display
                )

                // Payment amount input
                var paymentAmount by remember { mutableStateOf("") }
                var errorMessage by remember { mutableStateOf("") }

                TextField(
                    value = paymentAmount,
                    onValueChange = { input ->
                        paymentAmount = input
                        // Validate input: only numeric values or a single decimal point
                        errorMessage = when {
                            input.isEmpty() -> ""
                            !input.all { it.isDigit() || it == '.' } -> "Please enter a valid numeric amount."
                            else -> ""
                        }
                    },
                    label = { Text("Payment Amount") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("PaymentAmountField") // Tag for payment amount input
                )

                // Display error message if any
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.testTag("ErrorMessageTag") // Tag for error message
                    )
                }

                // Submit payment button
                Button(
                    onClick = {
                        val amount = paymentAmount.toDoubleOrNull()
                        if (amount != null) {
                            val error = viewModel.submitPayment(amount) // Handle payment submission
                            if (error == null) {
                                // Pass the isFundRequest parameter as needed
                                navController.navigate("confirmation_screen?amount=$amount&isFundRequest=false") // Navigate on success
                            } else {
                                errorMessage = error // Show error if payment submission fails
                            }
                        } else {
                            errorMessage = "Please enter a valid amount." // Show error if amount is invalid
                        }
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .testTag("SubmitPaymentButton") // Tag for submit button
                ) {
                    Text("Submit Payment")
                }

                // Request funds button
                Button(
                    onClick = {
                        navController.navigate("request_funds_screen") // Navigate to request funds screen
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .testTag("RequestFundsButton") // Tag for request funds button
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
                    .testTag("LogOutButton") // Tag for log out button
            ) {
                Text("Log Out")
            }
        }
    }
}
