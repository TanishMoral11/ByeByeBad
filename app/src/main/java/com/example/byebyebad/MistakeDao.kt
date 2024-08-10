package com.example.byebyebad

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface MistakeDao {

    @Insert
    suspend fun insert(mistake : Mistake)

    @Update
    suspend fun update(mistake: Mistake)

    @Query("SELECT * FROM mistake_table")
    suspend fun getAllMistakes() : List<Mistake>
}