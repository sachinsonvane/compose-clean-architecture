package com.sns.features.books.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sns.core.data.states.DataState
import com.sns.features.books.data.entity.Books
import com.sns.features.books.domain.usecases.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _books = mutableStateOf<DataState<List<Books>>>(DataState.Loading)
    val uiState: State<DataState<List<Books>>> = _books

    init {
        booksList()
    }

    private fun booksList() {
        _books.value = DataState.Loading
        viewModelScope.launch {
            try {
                val response = getBooksUseCase()
                _books.value = DataState.Success(response)
            } catch (e: Exception) {
                _books.value = DataState.Error("Failed to fetch data")
            }
        }
    }
}