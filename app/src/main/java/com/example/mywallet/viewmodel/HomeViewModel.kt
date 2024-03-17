package com.example.mywallet.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mywallet.R
import com.example.mywallet.data.dao.ExpenseDao
import com.example.mywallet.data.model.EntityExpense
import com.example.mywallet.data.WalletDB

class HomeViewModel(dao: ExpenseDao): ViewModel() {
    val expenses = dao.getAllExpenses()
    fun getBalance(list: List<EntityExpense>): String {
        var balance = 0.0
        for (i in list) {
            if (i.type == "Income") {
                balance += i.amount
            } else {
                balance -= i.amount
            }
        }
        return "EUR ${balance}"

    }

    fun getTotalExpense(list: List<EntityExpense>): String {
        var total = 0.0
        list.forEach {
            if (it.type == "Expense") {
                total += it.amount
            }
        }
        return "EUR ${total}"

    }

    fun getTotalIncome(list: List<EntityExpense>): String {
        var total = 0.0
        list.forEach {
            if (it.type == "Income") {
                total += it.amount
            }
        }
        return "EUR ${total}"

    }

    fun getItemIcon(item: EntityExpense): Int {
        if (item.type == "Income") {
            return R.drawable.income2
        } else {
            return R.drawable.expense1
        }
    }



    class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                val dao = WalletDB.getDatabase(context).expenseDao()
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(dao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}