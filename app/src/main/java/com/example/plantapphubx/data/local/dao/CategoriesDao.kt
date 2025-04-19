package com.example.plantapphubx.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plantapphubx.data.local.entitiy.CategoryEntity

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<CategoryEntity>)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): PagingSource<Int, CategoryEntity>

    @Query("DELETE FROM categories")
    suspend fun clearAll()
}