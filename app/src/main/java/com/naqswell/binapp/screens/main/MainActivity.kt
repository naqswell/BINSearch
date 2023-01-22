package com.naqswell.binapp.screens.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.naqswell.binapp.db.BinRepository
import com.naqswell.binapp.db.entities.Bin
import com.naqswell.binapp.networking.BinListApi
import com.naqswell.binapp.networking.entities.BinData
import com.naqswell.binapp.networking.entities.FetchBinData
import com.naqswell.binapp.screens.bindetail.BinDetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

class MainActivity : AppCompatActivity(), MainActivityViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var viewMvc: MainActivityViewMvc
    private lateinit var fetchBinData: FetchBinData
    private lateinit var binRepository: BinRepository
    private lateinit var binListApi: BinListApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewMvc = MainActivityViewMvc(layoutInflater)
        binListApi = BinListApi.get()
        binRepository = BinRepository.get()
        fetchBinData = FetchBinData()

        setContentView(viewMvc.root)
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        coroutineScope.launch {
            viewMvc.bindBins(binRepository.getBins())
        }
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    private fun fetchBinData(bin: Int) {
        coroutineScope.launch {
            try {
                val result = fetchBinData.fetchBinData(bin)
                when (result) {
                    is FetchBinData.Result.Success -> {
                        startBinDetailActivity(result.binData)
                        binRepository.addBin(Bin(bin = result.bin))
                        viewMvc.bindBins(binRepository.getBins())
                    }
                    is FetchBinData.Result.Failure -> {
                        onFetchFailed()
                    }
                }
            } finally {
            }
        }
    }

    private fun onFetchFailed() {
        Snackbar.make(
            viewMvc.root,
            "Invalid BIN or no internet connection",
            Snackbar.LENGTH_SHORT
        )
            .setAction("Ok") {}
            .show()
    }

    private fun startBinDetailActivity(bin: BinData) {
        val intent = Intent(this, BinDetailActivity::class.java)
        intent.putExtra("BinData", bin)
        startActivity(intent)
    }


    override fun onSearchBtnClicked(bin: Int) {
        if (bin.toString().length >= 4) {
            fetchBinData(bin)
        } else {
            Snackbar.make(
                viewMvc.root,
                "BIN must consist of at least 4 numbers",
                Snackbar.LENGTH_SHORT
            )
                .setAction("Ok") {}
                .show()
        }
    }

    override fun onClearHistoryBtnClicked() {
        coroutineScope.launch {
            binRepository.resetBin()
            viewMvc.bindBins(binRepository.getBins())
        }
    }
}