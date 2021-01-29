package com.kryptkode.bookfinder.data.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val datePublished: String,
    val pages: Int,
    val rating: Int,
    val ratingCount: Int
)