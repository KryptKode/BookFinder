package com.kryptkode.bookfinder.ui.booksearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kryptkode.bookfinder.data.model.Book
import com.kryptkode.bookfinder.data.model.DataState
import com.kryptkode.bookfinder.data.service.ServiceFactory
import com.kryptkode.bookfinder.data.service.mapper.BookResponseToBookListMapper
import com.kryptkode.bookfinder.data.service.mapper.ConvertUrlToHttps
import com.kryptkode.bookfinder.data.usecase.SearchBookUseCase
import com.kryptkode.bookfinder.util.extension.asLiveData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

@Suppress("EXPERIMENTAL_API_USAGE")
class BookSearchViewModel(
    private val bookUseCase: SearchBookUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<BookSearchViewState>()
    val viewState = mutableViewState.asLiveData()

    private val searchQuery = MutableSharedFlow<String>()

    private val stateReducer = { oldState: BookSearchViewState, dataState: DataState<List<Book>> ->
        when (dataState) {
            is DataState.Error -> oldState.copy(
                loading = false,
                error = true,
                errorMessage = dataState.message,
                books = emptyList()
            )
            is DataState.Loading -> oldState.copy(loading = true)
            is DataState.Success -> oldState.copy(
                loading = false,
                error = false,
                books = dataState.data
            )
        }
    }

    init {
        searchQuery
            .distinctUntilChanged()
            .flatMapLatest(bookUseCase::execute)
            .scan(BookSearchViewState()) { previous, result ->
                stateReducer(previous, result)
            }.onEach {
                mutableViewState.postValue(it)
            }
            .launchIn(viewModelScope)
    }

    suspend fun findBook(query: String) {
        searchQuery.emit(query)
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val apiService = ServiceFactory.createBookService()
                val useCase = SearchBookUseCase(
                    apiService, BookResponseToBookListMapper(
                        ConvertUrlToHttps()
                    )
                )
                return BookSearchViewModel(useCase) as T
            }
        }
    }

}