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

class RequestFundsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun requestFunds_withValidAmount_navigatesToConfirmationScreen() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Create an instance of MainViewModel
            NavHost(navController = navController, startDestination = "request_funds_screen") {
                composable("request_funds_screen") { RequestFundsScreen(navController, viewModel) }
                composable("confirmation_screen") { ConfirmationScreen(navController, amount = 100.0, isFundRequest = true) }
            }
        }

        // Enter a valid fund request amount
        composeTestRule.onNodeWithTag("PaymentAmountField").performTextInput("100.00")
        // Click submit payment button
        composeTestRule.onNodeWithTag("SubmitPaymentButton").performClick()
        // Verify that we navigate to the confirmation screen
        composeTestRule.onNodeWithTag("ConfirmationScreenTag").assertIsDisplayed()
    }

    @Test
    fun requestFunds_withInvalidAmount_showsErrorMessage() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Create an instance of MainViewModel
            NavHost(navController = navController, startDestination = "request_funds_screen") {
                composable("request_funds_screen") { RequestFundsScreen(navController, viewModel) }
            }
        }

        // Enter an invalid request amount
        composeTestRule.onNodeWithTag("PaymentAmountField").performTextInput("invalidAmount")
        // Click submit payment button
        composeTestRule.onNodeWithTag("SubmitPaymentButton").performClick()
        // Verify that the error message is displayed
        composeTestRule.onNodeWithTag("ErrorMessageTag").assertIsDisplayed()
    }

    @Test
    fun backToAccountButton_navigatesToAccountScreen() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Create an instance of MainViewModel
            NavHost(navController = navController, startDestination = "request_funds_screen") {
                composable("request_funds_screen") { RequestFundsScreen(navController, viewModel) }
                composable("account_screen") { AccountScreen(navController, viewModel) }
            }
        }

        // Click the back to account screen button
        composeTestRule.onNodeWithTag("BackToAccountButton").performClick()
        // Verify that we navigate back to the account screen
        composeTestRule.onNodeWithTag("AccountScreenTag").assertIsDisplayed()
    }
}
