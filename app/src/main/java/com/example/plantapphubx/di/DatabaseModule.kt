package com.example.plantapphubx.di

import android.content.Context
import androidx.room.Room
import com.example.plantapphubx.data.local.AppDatabase
import com.example.plantapphubx.data.local.dao.CategoriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCategoriesDao(appDatabase: AppDatabase): CategoriesDao {
        return appDatabase.categoriesDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "plantapphubx_database"
        ).build()
    }
}