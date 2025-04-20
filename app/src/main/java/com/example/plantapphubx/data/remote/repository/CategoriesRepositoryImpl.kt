package com.example.plantapphubx.data.remote.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.core.util.NetworkUtils
import com.example.plantapphubx.data.local.dao.CategoriesDao
import com.example.plantapphubx.data.local.entitiy.CategoryEntity
import com.example.plantapphubx.data.mapper.toEntity
import com.example.plantapphubx.data.mapper.toResponse
import com.example.plantapphubx.data.remote.api.APIService
import com.example.plantapphubx.data.remote.model.CategoriesResponse
import com.example.plantapphubx.data.remote.paging.CategoriesPagingSource
import com.example.plantapphubx.domain.repository.CategoriesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val categoryDao: CategoriesDao,
    @ApplicationContext private val context: Context
) : CategoriesRepository {

    override fun getCategories(): Flow<ApiResult<PagingData<CategoriesResponse>>> {
        return if (NetworkUtils.isNetworkAvailable(context)) {
            Pager(
                config = PagingConfig(
                    pageSize = 25,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { CategoriesPagingSource(apiService) }
            ).flow
                .map<PagingData<CategoriesResponse>, ApiResult<PagingData<CategoriesResponse>>> { pagingList ->
                    categoryDao.clearAll()
                    val categoriesList =
                        apiService.getCategories(pageSize = 25, page = 1).data.map { it.toEntity() }
                    categoryDao.insertAll(categoriesList)
                    ApiResult.Success(pagingList)
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
        } else Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { categoryDao.getAllCategories() }
        ).flow
            .map { pagingList ->
                val responsePagingData = pagingList.map { it.toResponse() }
                ApiResult.Success(responsePagingData)
            }
    }
}
