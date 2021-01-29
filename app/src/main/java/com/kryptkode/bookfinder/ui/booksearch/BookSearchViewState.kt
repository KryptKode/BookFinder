package com.kryptkode.bookfinder.ui.booksearch

import com.kryptkode.bookfinder.data.model.Book

data class BookSearchViewState(
    val loading: Boolean=false,
    val error: Boolean= false,
    val errorMessage: String="",
    val books: List<Book> = emptyList()
)