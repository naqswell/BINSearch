package com.naqswell.binapp.networking.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank(
    @field:Json(name = "name") var name: String? = null,
    @field:Json(name = "url") var url: String? = null,
    @field:Json(name = "phone") var phone: String? = null,
    @field:Json(name = "city") var city: String? = null
) : Parcelable