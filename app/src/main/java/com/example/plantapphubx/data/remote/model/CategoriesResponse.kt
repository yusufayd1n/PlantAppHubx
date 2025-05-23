package com.example.plantapphubx.data.remote.model

data class CategoriesWrapperResponse(
    val data: List<CategoriesResponse>,
    val meta: Meta
)

data class Meta(
    val pagination: Pagination
)

data class Pagination(
    val page: Int?,
    val pageSize: Int?,
    val pageCount: Int?,
    val total: Int?
)

data class CategoriesResponse(
    val createdAt: String?,
    val id: Int?,
    val image: image?,
    val name: String?,
    val publishedAt: String?,
    val rank: Int?,
    val title: String?,
    val updatedAt: String?
)

data class image(
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

