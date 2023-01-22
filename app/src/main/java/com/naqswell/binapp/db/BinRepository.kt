package com.naqswell.binapp.db

import android.content.Context
import androidx.room.Room
import com.naqswell.binapp.db.entities.Bin
import java.util.*

private const val DATABASE_NAME = "bin-database"

class BinRepository private constructor(context: Context) {
    private val database: BinDatabase =
        Room.databaseBuilder(context.applicationContext, BinDatabase::class.java, DATABASE_NAME)
            .build()

    private val binDAO: BinDAO = database.crimeDao()

    suspend fun getBins(): List<Bin> = binDAO.getBins()
    suspend fun getBin(id: UUID): Bin? = binDAO.getBin(id)
    suspend fun resetBin() = binDAO.resetBinTable()

    suspend fun updateBin(crime: Bin) {
        binDAO.updateBin(crime)
    }

    suspend fun addBin(crime: Bin) {
        binDAO.addBin(crime)
    }


    companion object {
        private var INSTANCE: BinRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = BinRepository(context)
            }
        }

        fun get(): BinRepository {
            return INSTANCE ?: throw IllegalStateException("Repository must be initialized")
        }
    }

}