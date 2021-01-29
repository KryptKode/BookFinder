package com.kryptkode.bookfinder.data.usecase

import com.kryptkode.bookfinder.data.dispatcher.AppDispatchers
import com.kryptkode.bookfinder.data.model.Book
import com.kryptkode.bookfinder.data.model.DataState
import com.kryptkode.bookfinder.data.service.BookService
import com.kryptkode.bookfinder.data.service.mapper.BookResponseToBookListMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchBookUseCase(
    private val bookService: BookService,
    private val mapper: BookResponseToBookListMapper,
    private val dispatchers: AppDispatchers
) {

    fun execute(query: String): Flow<DataState<List<Book>>> = flow {
        if (query.isEmpty()) {
            emit(DataState.Success(emptyList<Book>()))
        } else {
            emit(DataState.Loading)
            try {
                val result = bookService.searchBooks(query)
                emit(DataState.Success(mapper.mapToBookList(result)))
            } catch (e: Exception) {
                emit(DataState.Error(e.localizedMessage ?: "An error occurred"))
            }
        }
    }.flowOn(dispatchers.io)
}