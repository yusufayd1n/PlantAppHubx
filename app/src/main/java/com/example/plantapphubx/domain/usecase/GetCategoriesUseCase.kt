package com.example.plantapphubx.domain.usecase

import androidx.paging.PagingData
import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.data.remote.model.CategoriesResponse
import com.example.plantapphubx.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {
    operator fun invoke(): Flow<ApiResult<PagingData<CategoriesResponse>>> {
        return repository.getCategories()
    }
}