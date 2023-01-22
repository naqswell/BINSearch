package com.naqswell.binapp.screens.main

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naqswell.binapp.R
import com.naqswell.binapp.databinding.ActivityMainBinding
import com.naqswell.binapp.db.entities.Bin
import com.naqswell.binapp.screens.common.BaseViewMvc

class MainActivityViewMvc(layoutInflater: LayoutInflater) :
    BaseViewMvc<MainActivityViewMvc.Listener>(layoutInflater) {

    interface Listener {
        fun onSearchBtnClicked(bin: String)
    }

    private val binding = ActivityMainBinding.inflate(layoutInflater)
    val root = binding.root
    private val context: Context = root.context
    private lateinit var binAdapter: BinAdapter

    init {
        binding.apply {
            initBinCard(this)
            initRecyclerView(this)
        }
    }

    fun bindBins(questions: List<Bin>) {
        binAdapter.bindData(questions)
    }

    private fun initRecyclerView(binding: ActivityMainBinding) {
        with(binding) {
            rvHistory.layoutManager = LinearLayoutManager(context)
            binAdapter = BinAdapter()
            rvHistory.adapter = binAdapter
        }
    }

    private fun initBinCard(binding: ActivityMainBinding) {
        with(binding) {
            binCard.apply {

                txtInputLayout.editText?.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, keyEvent ->
                    if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent?.keyCode == KeyEvent.KEYCODE_ENTER) {
                        txtInputLayout.hideKeyboard()
                        return@OnEditorActionListener true
                    }
                    false
                })

                txtInputLayout.setEndIconOnClickListener {
                    val bin = txtInputLayout.editText?.text.toString()
                    for (listener in listeners) {
                        listener.onSearchBtnClicked(bin)
                    }
                }

                txtInputLayout.editText?.setOnFocusChangeListener { view, b ->
                }

            }
        }
    }


    internal class BinAdapter() :
        RecyclerView.Adapter<BinAdapter.BinViewHolder>() {

        private var binList: List<Bin> = ArrayList(0)

        inner class BinViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.txt_bin)
        }

        fun bindData(bins: List<Bin>) {
            binList = ArrayList(bins)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.bin_list_item, parent, false)
            return BinViewHolder(itemView)        }

        override fun onBindViewHolder(holder: BinViewHolder, position: Int) {
            holder.title.text = binList[position].bin
        }

        override fun getItemCount(): Int {
            return binList.size
        }

    }
}