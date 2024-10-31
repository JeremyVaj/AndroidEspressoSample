package com.example.paymentapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class ConfirmationScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayConfirmationMessage_forPayment() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Instantiate the ViewModel
            NavHost(navController = navController, startDestination = "confirmation_screen") {
                composable("confirmation_screen") {
                    ConfirmationScreen(navController, amount = 100.0, isFundRequest = false)
                }
            }
        }

        // Verify the payment confirmation message is displayed
        composeTestRule.onNodeWithText("Payment of $100.0 confirmed!").assertIsDisplayed()
        // Verify that the ConfirmationScreen is displayed
        composeTestRule.onNodeWithTag("ConfirmationScreenTag").assertIsDisplayed()
    }

    @Test
    fun displayConfirmationMessage_forFundRequest() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Instantiate the ViewModel
            NavHost(navController = navController, startDestination = "confirmation_screen") {
                composable("confirmation_screen") {
                    ConfirmationScreen(navController, amount = 150.0, isFundRequest = true)
                }
            }
        }

        // Verify the fund request confirmation message is displayed
        composeTestRule.onNodeWithText("Funds of $150.0 requested successfully!").assertIsDisplayed()
        // Verify that the ConfirmationScreen is displayed
        composeTestRule.onNodeWithTag("ConfirmationScreenTag").assertIsDisplayed()
    }

    @Test
    fun backToAccountScreenButton_navigatesToAccountScreen() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Instantiate the ViewModel
            NavHost(navController = navController, startDestination = "confirmation_screen") {
                composable("confirmation_screen") {
                    ConfirmationScreen(navController, amount = 100.0, isFundRequest = false)
                }
                composable("account_screen") {
                    AccountScreen(navController, viewModel)
                }
            }
        }

        // Click the "Back to Account Screen" button
        composeTestRule.onNodeWithText("Back to Account Screen").performClick()

        // Verify that we navigate back to the account screen using the correct test tag
        composeTestRule.onNodeWithTag("AccountScreenTag").assertIsDisplayed()
    }


}
