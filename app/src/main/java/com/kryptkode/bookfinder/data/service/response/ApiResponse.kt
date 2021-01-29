package com.kryptkode.bookfinder.data.service.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookResponse(
    @get:Json(name = "items") val items: List<BookItem>
)

@JsonClass(generateAdapter = true)
data class BookItem(
    @get:Json(name = "id") val id: String,
    @get:Json(name = "volumeInfo") val volumeInfo: BookVolumeInfo,
)

@JsonClass(generateAdapter = true)
data class BookVolumeInfo(
    @get:Json(name = "title") val title: String,
    @get:Json(name = "authors") val authors: List<String>?,
    @get:Json(name = "publishedDate") val publishedDate: String?,
    @get:Json(name = "pageCount") val pageCount: Int = 0,
    @get:Json(name = "averageRating") val rating: Float = 0f,
    @get:Json(name = "ratingsCount") val ratingCount: Int = 0,
    @get:Json(name = "imageLinks") val imageLinks: ImageLinks? = null
)

@JsonClass(generateAdapter = true)
data class ImageLinks(
    @get:Json(name = "smallThumbnail") val smallThumbnail: String = "",
    @get:Json(name = "thumbnail") val thumbnail: String = "",
)