package com.example.plantapphubx.data.local.entitiy

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: Int?,
    val name: String?,
    val title: String?,
    val createdAt: String?,
    val publishedAt: String?,
    val updatedAt: String?,
    val rank: Int?,
    @Embedded(prefix = "image_")
    val image: ImageEntity?
)

data class ImageEntity(
    val alternativeText: String?,
    val caption: String?,
    val createdAt: String?,
    val ext: String?,
    val formats: String?,
    val hash: String?,
    val height: Int?,
    val id: Int?,
    val mime: String?,
    val name: String?,
    val previewUrl: String?,
    val provider: String?,
    val provider_metadata: String?,
    val size: Double?,
    val updatedAt: String?,
    val url: String?,
    val width: Int?
)