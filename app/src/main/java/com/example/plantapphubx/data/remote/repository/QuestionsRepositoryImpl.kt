package com.example.plantapphubx.data.remote.repository

import android.content.Context
import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.core.util.NetworkUtils
import com.example.plantapphubx.data.local.dao.QuestionsDao
import com.example.plantapphubx.data.mapper.toEntity
import com.example.plantapphubx.data.mapper.toResponse
import com.example.plantapphubx.data.remote.api.APIService
import com.example.plantapphubx.data.remote.model.QuestionsResponse
import com.example.plantapphubx.domain.repository.QuestionsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val questionsDao: QuestionsDao,
    @ApplicationContext private val context: Context
) : QuestionsRepository {
    override suspend fun getQuestions(): Flow<ApiResult<List<QuestionsResponse>>> = flow {
        emit(ApiResult.Loading)

        if (NetworkUtils.isNetworkAvailable(context)) {
            try {
                val result = apiService.getQuestions()
                questionsDao.clearAll()
                val questionsList = apiService.getQuestions().map { it.toEntity() }
                questionsDao.insertAll(questionsList)
                emit(ApiResult.Success(result))
            } catch (e: Exception) {
                emit(ApiResult.Error(e))
            }
        } else {
            try {
                val localData = questionsDao.getAllQuestions().map { it.toResponse() }
                if (localData.isNotEmpty()) {
                    emit(ApiResult.Success(localData))
                } else {
                    emit(ApiResult.Error(Throwable("No data available locally.")))
                }
            } catch (e: Exception) {
                emit(ApiResult.Error(e))
            }
        }
    }.flowOn(Dispatchers.IO)
}