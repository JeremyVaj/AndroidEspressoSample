package com.example.paymentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentApp()
        }
    }

    @Composable
    fun PaymentApp() {
        val navController = rememberNavController()
        // Create an instance of MainViewModel here
        val viewModel: MainViewModel = viewModel()

        NavHost(navController, startDestination = "login_screen") {
            composable("login_screen") {
                LoginScreen(navController) // No need for viewModel here
            }
            composable("confirmation_screen?amount={amount}&isFundRequest={isFundRequest}") { backStackEntry ->
                val amountString = backStackEntry.arguments?.getString("amount")
                val isFundRequestString = backStackEntry.arguments?.getString("isFundRequest")
                val amount = amountString?.toDoubleOrNull() ?: 0.0
                val isFundRequest = isFundRequestString?.toBoolean() ?: false
                ConfirmationScreen(navController, amount, isFundRequest)
            }
            composable("account_screen") {
                AccountScreen(navController, viewModel) // Assuming you have an AccountScreen that needs the viewModel
            }
            composable("request_funds_screen") {
                RequestFundsScreen(navController, viewModel) // Add this line
            }
        }
    }
}
