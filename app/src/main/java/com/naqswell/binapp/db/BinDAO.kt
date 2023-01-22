package com.naqswell.binapp.db

import androidx.room.*
import com.naqswell.binapp.db.entities.Bin
import java.util.*

@Dao
interface BinDAO {
    @Query("SELECT * FROM bin")
    suspend fun getBins(): List<Bin>

    @Query("SELECT * FROM bin WHERE bin=(:id)")
    suspend fun getBin(id: UUID): Bin?

    @Update
    suspend fun updateBin(crime: Bin)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBin(crime: Bin)

    @Query("DELETE FROM bin")
    suspend fun resetBinTable()
}