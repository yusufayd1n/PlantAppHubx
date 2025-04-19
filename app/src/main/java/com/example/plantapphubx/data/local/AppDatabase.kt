package com.example.plantapphubx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plantapphubx.data.local.dao.CategoriesDao
import com.example.plantapphubx.data.local.entitiy.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
}