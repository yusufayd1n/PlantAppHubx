package com.example.plantapphubx.data.mapper

import com.example.plantapphubx.data.local.entitiy.CategoryEntity
import com.example.plantapphubx.data.local.entitiy.ImageEntity
import com.example.plantapphubx.data.local.entitiy.QuestionsEntity
import com.example.plantapphubx.data.remote.model.CategoriesResponse
import com.example.plantapphubx.data.remote.model.QuestionsResponse
import com.example.plantapphubx.data.remote.model.image

fun QuestionsResponse.toEntity(): QuestionsEntity {
    return QuestionsEntity(
        id = id,
        image_uri = image_uri,
        order = order,
        subtitle = subtitle,
        title = title,
        uri = uri
    )
}

fun QuestionsEntity.toResponse(): QuestionsResponse {
    return QuestionsResponse(
        id = id,
        image_uri = image_uri,
        order = order,
        subtitle = subtitle,
        title = title,
        uri = uri
    )
}
