package com.example.mywallet.data

import android.content.Context
import androidx.room.RoomDatabase
import com.example.mywallet.data.model.Entity
import androidx.room.Database
import com.example.mywallet.data.dao.Dao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Entity::class], version = 1)
abstract class WalletDB: RoomDatabase(){
    abstract fun dao(): Dao

    companion object{
        val DATABASE_NAME: String = "wallet_database"

        @JvmStatic
        fun getDatabase(context: android.content.Context): WalletDB {
            return androidx.room.Room.databaseBuilder(
                context.applicationContext,
                WalletDB::class.java,
                DATABASE_NAME
            ).addCallback(object : androidx.room.RoomDatabase.Callback() {
                override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                    super.onCreate(db)
                    InitBasicData(context)
                }
                fun InitBasicData(context: Context){
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = getDatabase(context).dao()
                        dao.insertData(Entity(1, "Salary", 1000.0, System.currentTimeMillis(), "Income", "Monthly Salary"))
                        dao.insertData(Entity(2, "Food", 100.0, System.currentTimeMillis(), "Expense", "Lunch"))
                        dao.insertData(Entity(3, "Transport", 50.0, System.currentTimeMillis(), "Expense", "Bus"))
                        dao.insertData(Entity(4, "Salary", 1000.0, System.currentTimeMillis(), "Income", "Monthly Salary"))
                        dao.insertData(Entity(5, "Food", 100.0, System.currentTimeMillis(), "Expense", "Lunch"))
                        dao.insertData(Entity(6, "Transport", 50.0, System.currentTimeMillis(), "Expense", "Bus"))
                    }
                }

            })
                .build()
        }
    }
}