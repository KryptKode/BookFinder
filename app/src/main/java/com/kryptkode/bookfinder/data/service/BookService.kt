package com.kryptkode.bookfinder.data.service

import com.kryptkode.bookfinder.data.service.response.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("books/v1/volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
    ): BookResponse
}

