package com.naqswell.binapp.networking.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class BinData(
    @field:Json(name = "number") var number: Number? = Number(),
    @field:Json(name = "scheme") var scheme: String? = null,
    @field:Json(name = "type") var type: String? = null,
    @field:Json(name = "brand") var brand: String? = null,
    @field:Json(name = "prepaid") var prepaid: Boolean? = null,
    @field:Json(name = "country") var country: Country? = Country(),
    @field:Json(name = "bank") var bank: Bank? = Bank()
) : Parcelable