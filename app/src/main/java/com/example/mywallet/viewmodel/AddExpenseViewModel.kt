package com.example.mywallet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywallet.data.dao.ExpenseRepository
import com.example.mywallet.data.model.EntityExpense
import kotlinx.coroutines.launch

class AddExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {
    fun insertExpense(expense: EntityExpense) {
        viewModelScope.launch {
            repository.insertExpense(expense)
        }
    }
}
