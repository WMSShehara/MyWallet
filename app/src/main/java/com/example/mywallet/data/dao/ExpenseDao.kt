package com.example.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mywallet.data.model.EntityExpense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM transaction_table")
    fun getAllExpenses(): Flow<List<EntityExpense>>

    @Insert
    suspend fun insertData(entity: EntityExpense)

    @Update
    suspend fun updateData(entity: EntityExpense)

    @Delete
    suspend fun deleteData(entity: EntityExpense)
}
