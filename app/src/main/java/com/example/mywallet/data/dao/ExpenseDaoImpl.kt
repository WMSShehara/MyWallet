package com.example.mywallet.data.dao

import com.example.mywallet.data.WalletDB
import com.example.mywallet.data.model.EntityExpense
import kotlinx.coroutines.flow.Flow

class ExpenseDaoImpl(private val expenseDatabase: WalletDB) : ExpenseDao {

    override fun getAllExpenses(): Flow<List<EntityExpense>> {
        return expenseDatabase.expenseDao().getAllExpenses()
    }

    override suspend fun insertData(entity: EntityExpense) {
        expenseDatabase.expenseDao().insertData(entity)
    }

    override suspend fun updateData(entity: EntityExpense) {
        expenseDatabase.expenseDao().updateData(entity)
    }

    override suspend fun deleteData(entity: EntityExpense) {
        expenseDatabase.expenseDao().deleteData(entity)
    }
}

