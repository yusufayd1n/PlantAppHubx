package com.example.plantapphubx.domain.usecase

import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.data.remote.model.QuestionsResponse
import com.example.plantapphubx.domain.repository.QuestionsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val repository: QuestionsRepository
) {
    suspend operator fun invoke(): Flow<ApiResult<List<QuestionsResponse>>> {
        return repository.getQuestions()
    }
}