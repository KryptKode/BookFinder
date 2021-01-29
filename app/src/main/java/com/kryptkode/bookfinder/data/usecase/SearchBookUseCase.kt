package com.kryptkode.bookfinder.data.usecase

import com.kryptkode.bookfinder.data.model.Book
import com.kryptkode.bookfinder.data.model.DataState
import com.kryptkode.bookfinder.data.service.BookService
import com.kryptkode.bookfinder.data.service.mapper.BookResponseToBookListMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchBookUseCase(
    private val bookService: BookService,
    private val mapper: BookResponseToBookListMapper
) {

    fun execute(query: String): Flow<DataState<List<Book>>> = flow {
        emit(DataState.Loading)
        try {
            val result = bookService.searchBooks(query)
            emit(DataState.Success(mapper.mapToBookList(result)))
        } catch (e: Exception) {
            emit(DataState.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}