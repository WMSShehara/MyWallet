package com.example.mywallet.data

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.RoomDatabase
import com.example.mywallet.data.model.EntityExpense
import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverter
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mywallet.data.dao.ExpenseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [EntityExpense::class], version = 1)
abstract class WalletDB: RoomDatabase(){
    abstract fun expenseDao(): ExpenseDao

    companion object{
        const val DATABASE_NAME: String = "walletDatabase"

        @JvmStatic
        fun getDatabase(context: Context): WalletDB {
            return Room.databaseBuilder(
                context,
                WalletDB::class.java,
                DATABASE_NAME
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    initBasicData(context)
                }
                fun initBasicData(context: Context){
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = getDatabase(context).expenseDao()
                        dao.insertData(EntityExpense(1, "Salary", 1000.0, System.currentTimeMillis(), "Income", "Monthly Salary"))
                        dao.insertData(EntityExpense(2, "Food", 100.0, System.currentTimeMillis(), "Expense", "Lunch"))
                        dao.insertData(EntityExpense(3, "Transport", 50.0, System.currentTimeMillis(), "Expense", "Bus"))
                        dao.insertData(EntityExpense(4, "Salary", 1000.0, System.currentTimeMillis(), "Income", "Monthly Salary"))
                        dao.insertData(EntityExpense(5, "Food", 100.0, System.currentTimeMillis(), "Expense", "Lunch"))
                        dao.insertData(EntityExpense(6, "Transport", 50.0, System.currentTimeMillis(), "Expense", "Bus"))
                    }
                }

            })
                .build()
        }

        fun getInstance(context: Context): WalletDB {
            return Room.databaseBuilder(
                context,
                WalletDB::class.java,
                DATABASE_NAME
            ).build()

        }
    }
}