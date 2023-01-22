package com.naqswell.binapp.networking.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Number(
    @field:Json(name = "length") var length: Int? = null,
    @field:Json(name = "luhn") var luhn: Boolean? = null
): Parcelable