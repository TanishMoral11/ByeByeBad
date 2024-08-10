package com.example.byebyebad

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "mistake_table")
data class Mistake(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title: String,
    val description: String,
    val count : Int = 0
)
