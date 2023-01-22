package com.naqswell.binapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.naqswell.binapp.db.entities.Bin
import java.util.*

@Dao
interface BinDAO {
    @Query("SELECT * FROM bin")
    suspend fun getBins(): List<Bin>

    @Query("SELECT * FROM bin WHERE id=(:id)")
    suspend fun getBin(id: UUID): Bin?

    @Update
    suspend fun updateBin(crime: Bin)

    @Insert
    suspend fun addBin(crime: Bin)
}