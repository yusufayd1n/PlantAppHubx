package com.example.plantapphubx.data.remote.dto

data class QuestionsResponse(
    val id: Int,
    val image_uri: String,
    val order: Int,
    val subtitle: String,
    val title: String,
    val uri: String
)