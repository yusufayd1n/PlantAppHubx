package com.example.plantapphubx.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plantapphubx.data.local.entitiy.QuestionsEntity

@Dao
interface QuestionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<QuestionsEntity>)

    @Query("SELECT * FROM questions")
    fun getAllQuestions(): List<QuestionsEntity>

    @Query("DELETE FROM questions")
    suspend fun clearAll()
}