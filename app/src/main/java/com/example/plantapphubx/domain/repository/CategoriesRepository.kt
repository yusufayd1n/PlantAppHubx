package com.example.plantapphubx.domain.repository

import androidx.paging.PagingData
import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.data.remote.model.CategoriesResponse
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    fun getCategories(): Flow<ApiResult<PagingData<CategoriesResponse>>>
}
