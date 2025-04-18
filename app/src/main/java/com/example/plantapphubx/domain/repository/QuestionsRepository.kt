package com.example.plantapphubx.domain.repository

import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.data.remote.model.QuestionsResponse
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {
    suspend fun getQuestions(): Flow<ApiResult<List<QuestionsResponse>>>
}
