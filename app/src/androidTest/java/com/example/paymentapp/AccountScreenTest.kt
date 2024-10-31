package com.example.paymentapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class AccountScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun submitPayment_withValidAmount_navigatesToConfirmationScreen() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Create an instance of MainViewModel
            NavHost(navController = navController, startDestination = "account_screen") {
                composable("account_screen") { AccountScreen(navController, viewModel) }
                composable("confirmation_screen") { ConfirmationScreen(navController, amount = 100.0, isFundRequest = false) }
            }
        }

        // Enter a valid payment amount
        composeTestRule.onNodeWithTag("PaymentAmountField").performTextInput("100.00")
        // Click submit payment button
        composeTestRule.onNodeWithTag("SubmitPaymentButton").performClick()
        // Verify that we navigate to confirmation screen
        composeTestRule.onNodeWithTag("ConfirmationScreenTag").assertIsDisplayed()
    }

    @Test
    fun submitPayment_withInvalidAmount_showsErrorMessage() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Create an instance of MainViewModel
            NavHost(navController = navController, startDestination = "account_screen") {
                composable("account_screen") { AccountScreen(navController, viewModel) }
            }
        }

        // Enter an invalid payment amount
        composeTestRule.onNodeWithTag("PaymentAmountField").performTextInput("invalidAmount")
        // Click submit payment button
        composeTestRule.onNodeWithTag("SubmitPaymentButton").performClick()
        // Verify that the error message is displayed
        composeTestRule.onNodeWithTag("ErrorMessageTag").assertIsDisplayed()
    }

    @Test
    fun requestFundsButton_navigatesToRequestFundsScreen() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Create an instance of MainViewModel
            NavHost(navController = navController, startDestination = "account_screen") {
                composable("account_screen") { AccountScreen(navController, viewModel) }
                composable("request_funds_screen") { RequestFundsScreen(navController, viewModel) } // Ensure this route matches
            }
        }

        // Click request funds button
        composeTestRule.onNodeWithTag("RequestFundsButton").performClick()
        // Verify that we navigate to request funds screen
        composeTestRule.onNodeWithTag("RequestFundsScreenTag").assertIsDisplayed()
    }

    @Test
    fun logoutButton_navigatesToLoginScreen() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Create an instance of MainViewModel
            NavHost(navController = navController, startDestination = "account_screen") {
                composable("account_screen") { AccountScreen(navController, viewModel) }
                composable("login_screen") { LoginScreen(navController) }
            }
        }

        // Click logout button
        composeTestRule.onNodeWithTag("LogOutButton").performClick()
        // Verify that we navigate to login screen
        composeTestRule.onNodeWithTag("LoginScreenTag").assertIsDisplayed()
    }
}
