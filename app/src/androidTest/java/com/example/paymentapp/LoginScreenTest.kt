package com.example.paymentapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginWithValidCredentials_navigatesToAccountScreen() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Create a ViewModel instance
            NavHost(navController = navController, startDestination = "login_screen") {
                composable("login_screen") { LoginScreen(navController) }
                composable("account_screen") { AccountScreen(navController, viewModel) } // Pass the ViewModel
            }
        }

        // Enter valid username
        composeTestRule.onNodeWithTag("UsernameField").performTextInput("test")
        // Enter valid password
        composeTestRule.onNodeWithTag("PasswordField").performTextInput("test")
        // Click login
        composeTestRule.onNodeWithTag("LoginButton").performClick()

        // Check that we navigate to account screen (based on an element displayed in AccountScreen)
        composeTestRule.onNodeWithTag("AccountScreenTag").assertIsDisplayed()
    }

    @Test
    fun loginWithInvalidCredentials_showsErrorMessage() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel() // Create a ViewModel instance
            NavHost(navController = navController, startDestination = "login_screen") {
                composable("login_screen") { LoginScreen(navController) }
                composable("account_screen") { AccountScreen(navController, viewModel) } // Pass the ViewModel
            }
        }

        // Enter invalid username
        composeTestRule.onNodeWithTag("UsernameField").performTextInput("wrongUser")
        // Enter invalid password
        composeTestRule.onNodeWithTag("PasswordField").performTextInput("wrongPass")
        // Click login
        composeTestRule.onNodeWithTag("LoginButton").performClick()

        // Verify that error message is displayed
        composeTestRule.onNodeWithTag("ErrorMessage").assertIsDisplayed()
    }
}

