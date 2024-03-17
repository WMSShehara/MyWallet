package com.example.mywallet

import CurrencyConverterApp
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.example.mywallet.data.WalletDB
import com.example.mywallet.data.dao.ExpenseDaoImpl
import com.example.mywallet.data.dao.ExpenseRepository
import com.example.mywallet.screens.AddExpense
import com.example.mywallet.viewmodel.AddExpenseViewModel
import com.example.mywallet.screens.HomeScreen
import com.example.mywallet.screens.WelcomeScreen

import android.content.Context
import com.example.mywallet.screens.CurrencyConverterScreen
import com.example.mywallet.viewmodel.CurrencyViewModel


@Preview(showBackground = true)
@Composable
fun NavigationController(context: Context) {
    val database = WalletDB.getDatabase(context)
    val expenseDaoImpl = ExpenseDaoImpl(database)
    val expenseRepository = ExpenseRepository(dao = expenseDaoImpl)
    val addExpenseViewModel = AddExpenseViewModel(repository = expenseRepository)

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("addExpense") {
            AddExpense(addExpenseViewModel = addExpenseViewModel, navController = navController)
        }
        composable("currencyConverter"){
            CurrencyConverterApp( currencyViewModel = CurrencyViewModel(), navController = navController)
        }
    }
}




