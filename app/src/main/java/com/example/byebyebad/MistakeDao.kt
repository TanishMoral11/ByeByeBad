package com.example.byebyebad

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MistakeDao {
    @Query("SELECT * FROM mistakes")
    suspend fun getAllMistakes(): List<Mistake>

    @Insert
    suspend fun insertMistake(mistake: Mistake)

    @Update
    suspend fun updateMistake(mistake: Mistake)

    @Query("UPDATE mistakes SET count = count + 1 WHERE id = :id")
    suspend fun incrementCount(id: Int)
}
