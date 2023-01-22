package com.naqswell.binapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naqswell.binapp.db.entities.Bin

@Database(entities = [Bin::class], version = 2, exportSchema = false)
abstract class BinDatabase : RoomDatabase() {
    abstract fun crimeDao(): BinDAO
}