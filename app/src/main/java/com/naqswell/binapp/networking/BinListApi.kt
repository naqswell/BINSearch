package com.naqswell.binapp.networking

import com.naqswell.binapp.networking.entities.BinData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface BinListApi {
    @GET("/{bin}")
    suspend fun getBinData(@Path("bin") bin: Int): Response<BinData>

    companion object {
        private var INSTANCE: Retrofit? = null

        private const val baseUrl = "https://lookup.binlist.net/"

        fun initialize() {
            if (INSTANCE == null) {
                INSTANCE =
                    Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build()
            }
        }

        fun get(): BinListApi =
            INSTANCE!!.create(BinListApi::class.java) ?: throw IllegalStateException("Repository must be initialized")
    }
}