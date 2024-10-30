package com.example.paymentapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var balance = mutableStateOf(100.0) // Keeps track of the user's balance
    var isLoggedIn = mutableStateOf(false) // Tracks if the user is logged in

    // Logs in the user based on a simple username/password check
    fun login(username: String, password: String): Boolean {
        isLoggedIn.value = (username == "test" && password == "test")
        return isLoggedIn.value
    }

    // Submits a payment and returns an error message if there's an issue
    fun submitPayment(amount: Double): String? {
        return when {
            amount <= 0 -> "Please enter a positive amount."
            amount > balance.value -> "Insufficient funds."
            else -> {
                balance.value -= amount // Deducts the amount from the balance
                null // Successful payment
            }
        }
    }

    // Requests funds and adds the requested amount to the balance
    fun requestFunds(amount: Double) {
        if (amount > 0) {
            balance.value += amount // Add the requested amount to the balance
        }
        // Additional logic can be added here, such as API calls for fund requests
    }
}
