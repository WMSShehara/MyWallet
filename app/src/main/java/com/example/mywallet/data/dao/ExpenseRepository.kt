package com.example.mywallet.data.dao

import com.example.mywallet.data.model.EntityExpense

class ExpenseRepository(private val dao: ExpenseDao) {
    suspend fun insertExpense(expense: EntityExpense) {
        dao.insertData(expense)
    }
}