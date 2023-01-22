package com.naqswell.binapp.screens.common

import android.view.LayoutInflater

open class BaseViewMvc<LISTENER_TYPE>(
    private val layoutInflater: LayoutInflater,
) {
    protected val listeners = HashSet<LISTENER_TYPE>()

    fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }
}