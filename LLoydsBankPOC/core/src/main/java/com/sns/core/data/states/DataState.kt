package com.sns.core.data.states

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: String) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
}