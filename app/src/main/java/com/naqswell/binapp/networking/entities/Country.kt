package com.naqswell.binapp.networking.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    @field:Json(name = "numeric") var numeric: String? = null,
    @field:Json(name = "alpha2") var alpha2: String? = null,
    @field:Json(name = "name") var name: String? = null,
    @field:Json(name = "emoji") var emoji: String? = null,
    @field:Json(name = "currency") var currency: String? = null,
    @field:Json(name = "latitude") var latitude: Int? = null,
    @field:Json(name = "longitude") var longitude: Int? = null
) : Parcelable