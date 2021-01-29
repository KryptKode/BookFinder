package com.kryptkode.bookfinder.data.service.mapper

import com.kryptkode.bookfinder.data.model.Book
import com.kryptkode.bookfinder.data.service.response.BookResponse
import java.util.Locale

class BookResponseToBookListMapper(
    private val convertUrlToHttps: ConvertUrlToHttps
) {
    fun mapToBookList(bookResponse: BookResponse): List<Book> {
        return bookResponse.items.map {
            Book(
                it.id,
                it.volumeInfo.title,
                it.volumeInfo.authors?.joinToString { author ->
                    author.capitalize(Locale.getDefault())
                } ?: "Anonymous",
                it.volumeInfo.publishedDate ?: "Unknown",
                it.volumeInfo.pageCount.toString(),
                it.volumeInfo.rating,
                it.volumeInfo.ratingCount.toString(),
                convertUrlToHttps.convert(it.volumeInfo.imageLinks?.thumbnail ?: "")
            )
        }
    }
}