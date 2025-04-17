package com.example.plantapphubx.di

import com.example.plantapphubx.data.remote.api.APIService
import com.example.plantapphubx.data.remote.repository.QuestionsRepositoryImpl
import com.example.plantapphubx.domain.repository.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}