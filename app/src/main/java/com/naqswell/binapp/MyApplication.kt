package com.naqswell.binapp

import android.app.Application
import com.naqswell.binapp.db.BinRepository
import com.naqswell.binapp.networking.BinListApi

class MyApplication: Application() {

    override fun onCreate() {
        BinListApi.initialize()
        BinRepository.initialize(this)
        super.onCreate()
    }
}