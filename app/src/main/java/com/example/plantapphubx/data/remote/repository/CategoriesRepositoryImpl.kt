package com.example.plantapphubx.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.data.remote.api.APIService
import com.example.plantapphubx.data.remote.model.CategoriesResponse
import com.example.plantapphubx.data.remote.model.QuestionsResponse
import com.example.plantapphubx.data.remote.paging.CategoriesPagingSource
import com.example.plantapphubx.domain.repository.CategoriesRepository
import com.example.plantapphubx.domain.repository.QuestionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : CategoriesRepository {

    override fun getCategories(): Flow<ApiResult<PagingData<CategoriesResponse>>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CategoriesPagingSource(apiService) }
        ).flow
            .map<PagingData<CategoriesResponse>, ApiResult<PagingData<CategoriesResponse>>> {
                ApiResult.Success(it)
            }
            .onStart {
                emit(ApiResult.Loading)
            }
            .catch { exception ->
                emit(
                    ApiResult.Error(
                        Throwable(
                            exception.localizedMessage ?: "Bilinmeyen bir hata oluştu"
                        )
                    )
                )
            }
    }
}