package com.example.plantapphubx.data.remote.api

import com.example.plantapphubx.data.remote.dto.QuestionsResponse
import retrofit2.http.GET

interface APIService {
    //TODO maybe we can hide or take this into variable
    @GET("getQuestions")
    suspend fun getQuestions(): List<QuestionsResponse>
}