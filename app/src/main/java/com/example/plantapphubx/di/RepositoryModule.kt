package com.example.plantapphubx.di

import android.content.Context
import com.example.plantapphubx.data.local.dao.CategoriesDao
import com.example.plantapphubx.data.remote.api.APIService
import com.example.plantapphubx.data.remote.repository.CategoriesRepositoryImpl
import com.example.plantapphubx.data.remote.repository.QuestionsRepositoryImpl
import com.example.plantapphubx.domain.repository.CategoriesRepository
import com.example.plantapphubx.domain.repository.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideQuestionsRepository(
        apiService: APIService
    ): QuestionsRepository {
        return QuestionsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(
        apiService: APIService,
        categoryDao: CategoriesDao,
        @ApplicationContext context: Context
    ): CategoriesRepository {
        return CategoriesRepositoryImpl(apiService,categoryDao,context)
    }
}