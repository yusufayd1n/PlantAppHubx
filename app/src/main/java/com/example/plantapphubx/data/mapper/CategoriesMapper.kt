package com.example.plantapphubx.data.mapper

import com.example.plantapphubx.data.local.entitiy.CategoryEntity
import com.example.plantapphubx.data.local.entitiy.ImageEntity
import com.example.plantapphubx.data.remote.model.CategoriesResponse
import com.example.plantapphubx.data.remote.model.image

fun CategoriesResponse.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        title = title,
        createdAt = createdAt,
        publishedAt = publishedAt,
        updatedAt = updatedAt,
        rank = rank,
        image = image?.toImageEntity()
    )
}

fun image.toImageEntity(): ImageEntity {
    return ImageEntity(
        url = url,
        height = height,
        width = width,
        name = name,
        ext = ext,
        hash = hash,
        mime = mime,
        previewUrl = previewUrl.toString(),
        provider = provider,
        provider_metadata = provider_metadata.toString(),
        size = size,
        updatedAt = updatedAt,
        alternativeText = alternativeText,
        caption = caption,
        createdAt = createdAt,
        formats = formats,
        id = id
    )
}

fun CategoryEntity.toResponse(): CategoriesResponse {
    return CategoriesResponse(
        id = id,
        name = name,
        title = title,
        createdAt = createdAt,
        publishedAt = publishedAt,
        updatedAt = updatedAt,
        image = image?.toImage(),
        rank = rank
    )
}

fun ImageEntity.toImage(): image {
    return image(
        url = url,
        height = height,
        width = width,
        name = name,
        ext = ext,
        hash = hash,
        mime = mime,
        previewUrl = previewUrl,
        provider = provider,
        provider_metadata = provider_metadata,
        size = size,
        updatedAt = updatedAt,
        alternativeText = alternativeText,
        caption = caption,
        createdAt = createdAt,
        formats = formats,
        id = id
    )
}