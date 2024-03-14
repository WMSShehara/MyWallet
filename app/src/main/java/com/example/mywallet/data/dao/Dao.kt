package com.example.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mywallet.data.model.Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {


@Query("SELECT * FROM transaction_table")
fun getAllData(): Flow<List<Entity>>

@Insert
suspend fun insertData(entity: Entity)

@Update
suspend fun updateData(entity: Entity)

@Delete
suspend fun deleteData(entity: Entity)
}