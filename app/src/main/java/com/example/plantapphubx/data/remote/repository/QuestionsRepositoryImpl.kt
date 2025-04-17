package com.example.plantapphubx.data.remote.repository

import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.data.remote.api.APIService
import com.example.plantapphubx.data.remote.dto.QuestionsResponse
import com.example.plantapphubx.domain.repository.QuestionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : QuestionsRepository {
    override suspend fun getQuestions(): Flow<ApiResult<List<QuestionsResponse>>> = flow {
        emit(ApiResult.Loading)
        try {
            val result = apiService.getQuestions()
            emit(ApiResult.Success(result))
        } catch (e: Exception) {
            emit(ApiResult.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}