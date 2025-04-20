package com.example.plantapphubx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plantapphubx.data.local.dao.CategoriesDao
import com.example.plantapphubx.data.local.dao.QuestionsDao
import com.example.plantapphubx.data.local.entitiy.CategoryEntity
import com.example.plantapphubx.data.local.entitiy.QuestionsEntity

@Database(
    entities = [CategoryEntity::class, QuestionsEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun questionsDao(): QuestionsDao
}