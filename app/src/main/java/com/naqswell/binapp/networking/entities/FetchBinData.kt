package com.naqswell.binapp.networking.entities

import com.naqswell.binapp.networking.BinListApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchBinData {

    private val binListApi = BinListApi.get()

    sealed class Result {
        class Success(val binData: BinData, val bin: String) : Result()
        class Failure(val errorData: String) : Result()
    }

    suspend fun fetchBinData(bin: String): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = binListApi.getBinData(bin)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!, bin)
                } else {
                    return@withContext Result.Failure(response.errorBody().toString())
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure("cancellationException")
                } else {
                    throw t
                }
            }
        }
    }
}