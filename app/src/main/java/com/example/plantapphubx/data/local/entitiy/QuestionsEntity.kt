package com.example.plantapphubx.data.local.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionsEntity(
    @PrimaryKey
    val id: Int?,
    val image_uri: String?,
    val order: Int?,
    val subtitle: String?,
    val title: String?,
    val uri: String?
)