package com.example.plantapphubx.data.remote.api

import com.example.plantapphubx.data.remote.model.CategoriesWrapperResponse
import com.example.plantapphubx.data.remote.model.QuestionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("getQuestions")
    suspend fun getQuestions(): List<QuestionsResponse>

    @GET("getCategories")
    suspend fun getCategories(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): CategoriesWrapperResponse
}