package com.example.plantapphubx.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.plantapphubx.data.remote.api.APIService
import com.example.plantapphubx.data.remote.model.CategoriesResponse

class CategoriesPagingSource(
    private val apiService: APIService
) : PagingSource<Int, CategoriesResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CategoriesResponse> {
        return try {
            val currentPage = params.key ?: 1
            val pageSize = params.loadSize

            val response = apiService.getCategories(currentPage, pageSize)

            val data = response.data
            val totalPages = response.meta.pagination.pageCount ?: 0

            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage < totalPages) currentPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CategoriesResponse>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}