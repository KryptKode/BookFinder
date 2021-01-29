package com.kryptkode.bookfinder.ui.booksearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kryptkode.bookfinder.data.model.Book
import com.kryptkode.bookfinder.data.model.DataState
import com.kryptkode.bookfinder.data.service.ServiceFactory
import com.kryptkode.bookfinder.data.service.mapper.BookResponseToBookListMapper
import com.kryptkode.bookfinder.data.usecase.SearchBookUseCase
import com.kryptkode.bookfinder.util.extension.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

class BookSearchViewModel(
    private val bookUseCase: SearchBookUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<BookSearchViewState>()
    val viewState = mutableViewState.asLiveData()

    private val stateReducer = { oldState: BookSearchViewState, dataState: DataState<List<Book>> ->
        when (dataState) {
            is DataState.Error -> oldState.copy(
                loading = false,
                error = true,
                errorMessage = dataState.message
            )
            is DataState.Loading -> oldState.copy(loading = true, error = false)
            is DataState.Success -> oldState.copy(
                loading = false,
                error = false,
                books = dataState.data
            )
        }
    }

    @ExperimentalCoroutinesApi
    fun findBook(query: String) {
        bookUseCase.execute(query)
            .scan(BookSearchViewState()) { previous, result ->
                stateReducer(previous, result)
            }.onEach {
                mutableViewState.postValue(it)
            }
            .launchIn(viewModelScope)
    }

    companion object {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val apiService = ServiceFactory.createBookService()
                val useCase = SearchBookUseCase(apiService, BookResponseToBookListMapper())
                return BookSearchViewModel(useCase) as T
            }
        }
    }

}