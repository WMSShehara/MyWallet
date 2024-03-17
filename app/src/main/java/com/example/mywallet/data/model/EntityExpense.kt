package com.example.mywallet.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class EntityExpense (
    @PrimaryKey(autoGenerate = true)
        val id: Int=0,
        val title: String,
        val amount: Double,
        val date: Long,
        val type: String,
        val description: String)