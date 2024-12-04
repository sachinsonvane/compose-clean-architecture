package com.sns.features.books.data.repository

import com.sns.features.books.data.remote.ApiServices
import com.sns.features.books.data.entity.Books
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val apiServices: ApiServices
) {
    suspend fun getBooksApiCall(): List<Books> = apiServices.getAllBooks()

}