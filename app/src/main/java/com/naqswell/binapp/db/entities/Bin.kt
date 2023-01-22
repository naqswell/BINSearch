package com.naqswell.binapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bin(
    @PrimaryKey
    @ColumnInfo
    val bin: Int,
)