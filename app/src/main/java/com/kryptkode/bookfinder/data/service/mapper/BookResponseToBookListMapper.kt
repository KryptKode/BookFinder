package com.kryptkode.bookfinder.data.service.mapper

import com.kryptkode.bookfinder.data.model.Book
import com.kryptkode.bookfinder.data.service.response.BookResponse
import java.util.*

class BookResponseToBookListMapper {
    fun mapToBookList(bookResponse: BookResponse): List<Book> {
        return bookResponse.items.map {
            Book(
                it.id,
                it.volumeInfo.title,
                it.volumeInfo.authors.joinToString { author -> author.capitalize(Locale.getDefault()) },
                it.volumeInfo.publishedDate,
                it.volumeInfo.pageCount,
                it.volumeInfo.rating,
                it.volumeInfo.ratingCount
            )
        }
    }
}