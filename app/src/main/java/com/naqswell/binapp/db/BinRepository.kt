package com.naqswell.binapp.db

import android.content.Context
import androidx.room.Room
import com.naqswell.binapp.db.entities.Bin
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

private const val DATABASE_NAME = "bin-database"

class BinRepository private constructor(context: Context) {
    private val database: BinDatabase =
        Room.databaseBuilder(context.applicationContext, BinDatabase::class.java, DATABASE_NAME)
            .build()

    private val crimeDao: BinDAO = database.crimeDao()
//    private val executor: Executor = Executors.newSingleThreadExecutor()

    suspend fun getBins(): List<Bin> = crimeDao.getBins()
    suspend fun getBin(id: UUID): Bin? = crimeDao.getBin(id)

    suspend fun updateBin(crime: Bin) {
//        executor.execute {
//            crimeDao.updateBin(crime)
//        }
        crimeDao.updateBin(crime)
    }

    suspend fun addBin(crime: Bin) {
//        executor.execute {
//            crimeDao.updateBin(crime)
//        }
        crimeDao.addBin(crime)
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