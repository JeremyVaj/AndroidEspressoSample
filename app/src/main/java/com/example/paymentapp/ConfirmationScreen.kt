package com.example.paymentapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ConfirmationScreen(navController: NavController, amount: Double, isFundRequest: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("ConfirmationScreenTag"), // Add test tag for confirmation screen
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            // Display the confirmation message based on the type
            val confirmationMessage = if (isFundRequest) {
                "Funds of $$amount requested successfully!"
            } else {
                "Payment of $$amount confirmed!"
            }

            // Display the confirmation message with larger font size
            Text(
                text = confirmationMessage,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Button to go back to the Account screen
            Button(
                onClick = {
                    navController.navigate("account_screen") // Navigate to AccountScreen explicitly
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Back to Account Screen")
            }
        }
    }
}
