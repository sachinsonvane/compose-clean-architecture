package com.sns.features.books.data.remote

import com.sns.core.constants.AppConstants
import com.sns.features.books.data.entity.Books
import retrofit2.http.GET

interface ApiServices {

    @GET(AppConstants.BOOKS_URL)
    suspend fun getAllBooks(): List<Books>
}